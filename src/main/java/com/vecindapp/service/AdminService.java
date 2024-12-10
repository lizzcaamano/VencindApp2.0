package com.vecindapp.service;

import com.vecindapp.entity.Rol;
import com.vecindapp.entity.Usuario;
import com.vecindapp.entity.UsuarioRol;
import com.vecindapp.repository.dao.IUsuarioDAO;
import com.vecindapp.repository.dao.IUsuarioRolDAO;
import com.vecindapp.repository.dto.AdministradorDTO;
import com.vecindapp.repository.dto.ClienteDTO;
import com.vecindapp.repository.dto.IAdministradorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService implements IAdminService {

    @Autowired
    IAdministradorMapper adminmapper;

    @Autowired
    IUsuarioDAO userDAO;
    @Autowired
    IUsuarioRolDAO usrolDAO;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public AdministradorDTO findById(int id) {
        Usuario admin = userDAO.findById(id);
        return adminmapper.toDto(admin);
    }

    @Override
    public List<AdministradorDTO> ListAdmin() {
        List<Usuario> usuarios= userDAO.ListUsuarios();

        List<Usuario> administradores = usuarios.stream()
                .filter(usuario -> usuario.getUsuarioRols().stream()
                        .anyMatch(usuarioRol -> usuarioRol.getRole().getId() == 1)) // Rol de cliente
                .collect(Collectors.toList());

        return administradores.stream().map(usuario ->
                        adminmapper.toDto(usuario))
                .collect(Collectors.toList());

    }

    @Override
    public List<AdministradorDTO> insertAdmin(AdministradorDTO administradorDTO) {
        // Hashear la contraseña antes de guardar
        administradorDTO.setPassword(passwordEncoder.encode(administradorDTO.getPassword()));

        Usuario admin = adminmapper.toEntity(administradorDTO);
        userDAO.insertUsuario(admin);

        // Crear el objeto Rol con el id 1 (el id del rol cliente)
        Rol rolCliente = new Rol();
        rolCliente.setId(1);  // Asignar el id del rol cliente

        // Asociar el rol al UsuarioRol
        UsuarioRol usuarioRol = new UsuarioRol();
        usuarioRol.setRole(rolCliente);
        // Asociar el UsuarioRol al usuario
        usuarioRol.setUser(admin);

        usrolDAO.insertUsuarioRol(usuarioRol);

        adminmapper.toDto(admin);

        return ListAdmin();
    }

    @Override
    public AdministradorDTO updateAdmin(Integer id, AdministradorDTO administradorDTO) {
        Usuario admin = adminmapper.toEntity(administradorDTO);
        Usuario admins = userDAO.findById(id);

        // Actualizar solo la contraseña si es nueva
        if (administradorDTO.getPassword() != null && !administradorDTO.getPassword().isEmpty()) {
            admins.setPassword(passwordEncoder.encode(administradorDTO.getPassword()));
        }

        // Actualizar los campos del usuario
        admins.setNombre(administradorDTO.getNombre());
        admins.setEmail(administradorDTO.getEmail());
        admins.setPassword(administradorDTO.getPassword());
        admins.setTelefono(administradorDTO.getTelefono());

        userDAO.updateUsuario(admins);


        return adminmapper.toDto(admins);
    }

    @Override
    public List<AdministradorDTO> findByNombre(String nombre) {
        List<AdministradorDTO> admins = ListAdmin();


        // Filtrar los usuarios por nombre y mapearlos a DTO
        return admins.stream()
                .filter(cliente -> cliente.getNombre() != null &&
                        cliente.getNombre().toLowerCase().startsWith(nombre.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public AdministradorDTO updateByEstado(int id, String estado) {
        int actualizo = userDAO.updateByEstado(id, estado);

        if (actualizo > 0) {
            //Traer al trabajador actualizado
            Usuario cliente = userDAO.findById(id);
            return adminmapper.toDto(cliente);
        }
        return null ;
    }
}

