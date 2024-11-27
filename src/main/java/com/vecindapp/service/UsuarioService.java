package com.vecindapp.service;

import com.vecindapp.entity.Calificacion;
import com.vecindapp.entity.Usuario;
import com.vecindapp.repository.dao.IUsuarioDAO;
import com.vecindapp.repository.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    IUsuarioDAO usudao;


    @Override
    public UsuarioDTO findById(int id) {
        return null;
    }

    @Override
    public List<UsuarioDTO> ListUsuarios() {
        return List.of();
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
