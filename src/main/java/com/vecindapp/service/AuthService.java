package com.vecindapp.service;

import com.vecindapp.entity.Usuario;
import com.vecindapp.repository.dto.IUsuarioMapper;
import com.vecindapp.repository.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {


    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    IUsuarioMapper usuarioMapper;

    // Método para obtener el usuario autenticado
    public Usuario obtenerUsuarioActual() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("Usuario no autenticado context");
        }
        System.out.println("Authentication context: " + authentication);
        if (authentication != null) {
            System.out.println("Usuario autenticado context: " + authentication.getName());
            User userDetails = (User) authentication.getPrincipal();
            String username = userDetails.getUsername();

            // Busca los usuarios por nombre
            List<UsuarioDTO> usuariosDTO = usuarioService.findByNombre(username);
            if (!usuariosDTO.isEmpty()) {
                // Convierte el primer UsuarioDTO a Usuario usando el mapper
                return usuarioMapper.toEntity(usuariosDTO.get(0));
            }
        }
        return null; // Si no hay autenticación o no se encuentra el usuario
    }


}

