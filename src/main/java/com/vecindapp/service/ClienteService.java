package com.vecindapp.service;

import com.vecindapp.entity.*;
import com.vecindapp.repository.dao.IUsuarioDAO;
import com.vecindapp.repository.dao.IUsuarioRolDAO;
import com.vecindapp.repository.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ClienteService implements IClienteService {

    @Autowired
    IUsuarioDAO userDAO;
    @Autowired
    IClienteMapper climap;
    @Autowired
    IUsuarioRolDAO usrolDAO;

    @Autowired
    IUbicacionService ubiservice;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Override
    public ClienteDTO findById(int id) {
        Usuario user = userDAO.findById(id);
        return climap.toDto(user);
    }

    @Override
    public List<ClienteDTO> ListClientes() {
        List<Usuario> usuarios= userDAO.ListUsuarios();

        List<Usuario> clientes = usuarios.stream()
                .filter(usuario -> usuario.getUsuarioRols().stream()
                        .anyMatch(usuarioRol -> usuarioRol.getRole().getId() == 3)) // Rol de cliente
                .collect(Collectors.toList());

        return clientes.stream().map(usuario ->
                climap.toDto(usuario))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<ClienteDTO> insertCliente(ClienteDTO clientedto) {

        Optional<Usuario> emailExists = userDAO.findByEmail(clientedto.getEmail());
        if (emailExists.isPresent()) {
            throw new RuntimeException("El correo ya existe, intente iniciar sesión");
        }

        // Hashear la contraseña antes de guardar
        clientedto.setPassword(passwordEncoder.encode(clientedto.getPassword()));

        Usuario cliente = climap.toEntity(clientedto);

        //Para crear ubicacion
        Barrio barrio = new Barrio();
        barrio.setNombreBarrio(clientedto.getNombreBarrio());

        Localidad localidad = new Localidad();
        localidad.setNombreLocalidad(clientedto.getNombreLocalidad());

        Ciudad ciudad = new Ciudad();
        ciudad.setNombreCiudad(clientedto.getNombreCiudad());

        //Asigancion de informacion
        localidad.setCiudad(ciudad);
        barrio.setLocalidad(localidad);


        Ubicacion ubicacion = new Ubicacion();
        ubicacion.setDireccion(clientedto.getUbicaciondir());
        ubicacion.setBarrio(barrio);
        // Manejar el barrio y otros detalles
        ubiservice.insertUbicacion(ubicacion);
        cliente.setUbicacion(ubicacion);
        userDAO.insertUsuario(cliente);


        // Crear el objeto Rol con el id 3 (el id del rol cliente)
        Rol rolCliente = new Rol();
        rolCliente.setId(3);  // Asignar el id del rol cliente

        // Asociar el rol al UsuarioRol
        UsuarioRol usuarioRol = new UsuarioRol();
        usuarioRol.setRole(rolCliente);
        // Asociar el UsuarioRol al usuario
        usuarioRol.setUser(cliente);

        usrolDAO.insertUsuarioRol(usuarioRol);

        climap.toDto(cliente);

        return ListClientes();
    }

    @Override
    public ClienteDTO updateCliente(Integer id, ClienteDTO clientedto) {
        Usuario cliente = climap.toEntity(clientedto);
        Usuario cliSingle = userDAO.findById(id);

        // Actualizar solo la contraseña si es nueva
        if (clientedto.getPassword() != null && !clientedto.getPassword().isEmpty()) {
            cliSingle.setPassword(passwordEncoder.encode(clientedto.getPassword()));
        }

        // Actualizar los campos del usuario
        cliSingle.setNombre(clientedto.getNombre());
        cliSingle.setEmail(clientedto.getEmail());
        cliSingle.setFoto(clientedto.getFoto());
        cliSingle.setPassword(clientedto.getPassword());
        cliSingle.setTelefono(clientedto.getTelefono());
        cliSingle.setDescripcion(clientedto.getDescripcion());
        cliSingle.setFechaRegistro(clientedto.getFechaRegistro());

        //Para actualizar ubicacion
        Barrio barrio = new Barrio();
        barrio.setNombreBarrio(clientedto.getNombreBarrio());

        Localidad localidad = new Localidad();
        localidad.setNombreLocalidad(clientedto.getNombreLocalidad());

        Ciudad ciudad = new Ciudad();
        ciudad.setNombreCiudad(clientedto.getNombreCiudad());

        //Asigancion de informacion
        localidad.setCiudad(ciudad);
        barrio.setLocalidad(localidad);


        Ubicacion ubicacion = new Ubicacion();
        ubicacion.setDireccion(clientedto.getUbicaciondir());
        ubicacion.setBarrio(barrio);

        // Manejar el barrio y otros detalles
        ubiservice.updateUbicacion(ubicacion);
        cliSingle.setUbicacion(ubicacion);

        userDAO.updateUsuario(cliSingle);


        return climap.toDto(cliSingle);
    }

    @Override
            public List<ClienteDTO> findByNombre(String nombre) {
                List<ClienteDTO> clientes = ListClientes();


                // Filtrar los usuarios por nombre y mapearlos a DTO
                 return clientes.stream()
                    .filter(cliente -> cliente.getNombre() != null &&
                            cliente.getNombre().toLowerCase().startsWith(nombre.toLowerCase()))
                    .collect(Collectors.toList());

            }

    @Override
    public ClienteDTO updateByEstado(int id, String estado) {
        int actualizo = userDAO.updateByEstado(id, estado);

        if (actualizo > 0) {
            //Traer al trabajador actualizado
            Usuario cliente = userDAO.findById(id);
            return climap.toDto(cliente);
        }
        return null ;
    }
}
