package com.vecindapp.service;

import com.vecindapp.entity.*;
import com.vecindapp.repository.dao.IUsuarioDAO;
import com.vecindapp.repository.dao.IUsuarioRolDAO;
import com.vecindapp.repository.dto.ClienteDTO;
import com.vecindapp.repository.dto.IClienteMapper;
import com.vecindapp.repository.dto.ITrabajadorMapper;
import com.vecindapp.repository.dto.TrabajadorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class TrabajadorService implements ITrabajadorService {

    @Autowired
    IUsuarioDAO userDAO;
    @Autowired
    ITrabajadorMapper tramap;
    @Autowired
    IUsuarioRolDAO usrolDAO;

    @Autowired
    IUbicacionService ubiservice;
    @Autowired
    IDocumentoService docservice;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Override
    public TrabajadorDTO findById(int id) {
        Usuario trabajador = userDAO.findById(id);

        return tramap.toDto(trabajador);
    }

    @Override
    public List<TrabajadorDTO> ListTrabajadores() {
        List<Usuario> usuarios= userDAO.ListUsuarios();

        List<Usuario> trabajadores = usuarios.stream()
                .filter(usuario -> usuario.getUsuarioRols().stream()
                        .anyMatch(usuarioRol -> usuarioRol.getRole().getId() == 2)) // Rol de cliente
                .collect(Collectors.toList());

        return trabajadores.stream().map(usuario ->
                        tramap.toDto(usuario))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<TrabajadorDTO> insertTrabajador(TrabajadorDTO trabajadordto) {

        Usuario emailExists = userDAO.findByEmail(trabajadordto.getEmail());

        // Hashear la contraseña antes de guardar
        trabajadordto.setPassword(passwordEncoder.encode(trabajadordto.getPassword()));

        Usuario trabajador = tramap.toEntity(trabajadordto);


        //Estado por defecto al crear el trabajador debe ser "pendiente"
        trabajador.setEstado("pendiente");
        //Para crear ubicacion
        Barrio barrio = new Barrio();
        barrio.setNombreBarrio(trabajadordto.getNombreBarrio());

        Localidad localidad = new Localidad();
        localidad.setNombreLocalidad(trabajadordto.getNombreLocalidad());

        Ciudad ciudad = new Ciudad();
        ciudad.setNombreCiudad(trabajadordto.getNombreCiudad());

        //Asigancion de informacion
        localidad.setCiudad(ciudad);
        barrio.setLocalidad(localidad);


        Ubicacion ubicacion = new Ubicacion();
        ubicacion.setDireccion(trabajadordto.getUbicaciondir());
        ubicacion.setBarrio(barrio);
        // Manejar el barrio y otros detalles
        ubiservice.insertUbicacion(ubicacion);
        trabajador.setUbicacion(ubicacion);
        userDAO.insertUsuario(trabajador);


        // Crear el objeto Rol con el id 3 (el id del rol cliente)
        Rol rolTrabajador = new Rol();
        rolTrabajador.setId(2);  // Asignar el id del rol cliente

        // Asociar el rol al UsuarioRol
        UsuarioRol usuarioRol = new UsuarioRol();
        usuarioRol.setRole(rolTrabajador);
        // Asociar el UsuarioRol al usuario
        usuarioRol.setUser(trabajador);

        usrolDAO.insertUsuarioRol(usuarioRol);

        // Crear y asociar documentos
        List<Documento> documentos = tramap.mapDocumentos(trabajadordto);
        for (Documento documento : documentos) {
            documento.setUser(trabajador);  // Asociar el trabajador al documento
            // Guardar documento
            docservice.guardarDoc(documento);
        }

        tramap.toDto(trabajador);

        return ListTrabajadores();
    }

    @Override
    public TrabajadorDTO updateTrabajador(Integer id, TrabajadorDTO trabajadordto) {
        Usuario trabajador = tramap.toEntity(trabajadordto);
        Usuario traSingle = userDAO.findById(id);

        // Actualizar solo la contraseña si es nueva
        if (trabajadordto.getPassword() != null && !trabajadordto.getPassword().isEmpty()) {
            traSingle.setPassword(passwordEncoder.encode(trabajadordto.getPassword()));
        }

        // Actualizar los campos del usuario
        traSingle.setNombre(trabajadordto.getNombre());
        traSingle.setEmail(trabajadordto.getEmail());
        traSingle.setFoto(trabajadordto.getFoto());
        traSingle.setPassword(trabajadordto.getPassword());
        traSingle.setTelefono(trabajadordto.getTelefono());
        traSingle.setDescripcion(trabajadordto.getDescripcion());
        traSingle.setFechaRegistro(trabajadordto.getFechaRegistro());

        //Para actualizar ubicacion
        Barrio barrio = new Barrio();
        barrio.setNombreBarrio(trabajadordto.getNombreBarrio());

        Localidad localidad = new Localidad();
        localidad.setNombreLocalidad(trabajadordto.getNombreLocalidad());

        Ciudad ciudad = new Ciudad();
        ciudad.setNombreCiudad(trabajadordto.getNombreCiudad());

        //Asigancion de informacion
        localidad.setCiudad(ciudad);
        barrio.setLocalidad(localidad);


        Ubicacion ubicacion = new Ubicacion();
        ubicacion.setDireccion(trabajadordto.getUbicaciondir());
        ubicacion.setBarrio(barrio);

        // Manejar el barrio y otros detalles
        ubiservice.updateUbicacion(ubicacion);
        traSingle.setUbicacion(ubicacion);

        userDAO.updateUsuario(traSingle);


        return tramap.toDto(traSingle);
    }

            @Override
            public List<TrabajadorDTO> findByNombre(String nombre) {
                List<TrabajadorDTO> trabajadores = ListTrabajadores();


                // Filtrar los usuarios por nombre y mapearlos a DTO
                 return trabajadores.stream()
                    .filter(trabajador -> trabajador.getNombre() != null &&
                            trabajador.getNombre().toLowerCase().startsWith(nombre.toLowerCase()))
                    .collect(Collectors.toList());
            }

    @Override
    public TrabajadorDTO updateByEstado(int id, String estado) {
       int actualizo = userDAO.updateByEstado(id, estado);

       if (actualizo > 0) {
           //Traer al trabajador actualizado
           Usuario trabajador = userDAO.findById(id);
           return tramap.toDto(trabajador);
       }
        return null ;
    }




}
