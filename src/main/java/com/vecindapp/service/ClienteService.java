package com.vecindapp.service;

import com.vecindapp.entity.Rol;
import com.vecindapp.entity.Ubicacion;
import com.vecindapp.entity.Usuario;
import com.vecindapp.entity.UsuarioRol;
import com.vecindapp.repository.dao.IUsuarioDAO;
import com.vecindapp.repository.dao.IUsuarioRolDAO;
import com.vecindapp.repository.dto.ClienteDTO;
import com.vecindapp.repository.dto.IClienteMapper;
import com.vecindapp.repository.dto.UbicacionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
        Usuario usuario = climap.toEntity(clientedto);

        Ubicacion ubicacion = new Ubicacion();
        UbicacionDTO ubicacionDTO = clientedto.getUbicacion();
        ubicacion.setDireccion(ubicacionDTO.getDireccion());
        // Manejar el barrio y otros detalles
        ubiservice.insertUbicacion(ubicacionDTO);

        userDAO.insertUsuario(usuario);


        // Crear el objeto Rol con el id 3 (el id del rol cliente)
        Rol rolCliente = new Rol();
        rolCliente.setId(3);  // Asignar el id del rol cliente

        // Asociar el rol al UsuarioRol
        UsuarioRol usuarioRol = new UsuarioRol();
        usuarioRol.setRole(rolCliente);
        // Asociar el UsuarioRol al usuario
        usuarioRol.setUser(usuario);

        usrolDAO.insertUsuarioRol(usuarioRol);

        climap.toDto(usuario);

        return ListClientes();
    }

    @Override
    public ClienteDTO updateCliente(Integer id, ClienteDTO clienteDTO) {
        return null;
    }

    @Override
    public List<ClienteDTO> findByNombre(String nombre) {
        return List.of();
    }
}
