package com.vecindapp.service;

import com.vecindapp.entity.Calificacion;
import com.vecindapp.entity.Usuario;
import com.vecindapp.repository.dao.IUsuarioDAO;
import com.vecindapp.repository.dto.IUsuarioMapper;
import com.vecindapp.repository.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    IUsuarioDAO usudao;

    @Autowired
    IUsuarioMapper usMap;


    @Override
    public UsuarioDTO findById(int id) {
        return null;
    }

    @Transactional
    @Override
    public List<UsuarioDTO> ListUsuarios() {
        List<Usuario> usuarios = usudao.ListUsuarios();
        return usuarios.stream()
                .map( usuario -> {
                    UsuarioDTO usuarioDTO = new UsuarioDTO();
                        usuarioDTO.setId(usuario.getId());
                        usuarioDTO.setNombre(usuario.getNombre());
                        usuarioDTO.setEmail(usuario.getEmail());
                        usuarioDTO.setFoto(usuario.getFoto());
                        usuarioDTO.setPassword(usuario.getPassword());
                        usuarioDTO.setTelefono(usuario.getTelefono());
                        usuarioDTO.setDireccion(usuario.getDireccion());
                        usuarioDTO.setUbicacionId(usuario.getUbicacion().getId());
                        usuarioDTO.setBarriod(usuarioDTO.getBarriod());
                        usuarioDTO.setDescripcion(usuario.getDescripcion());
                        usuarioDTO.setFechaRegistro(usuario.getFechaRegistro());
                        usuarioDTO.setEstado(usuario.getEstado());
                            return usuarioDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<UsuarioDTO> insertUsuario(UsuarioDTO usuariodto) {

        return List.of();
    }

    @Override
    public UsuarioDTO updateUsuario(UsuarioDTO usuariodto) {
        return null;
    }

    @Override
    public List<Usuario> findByNombre(String nombre) {
        return List.of();
    }
}
