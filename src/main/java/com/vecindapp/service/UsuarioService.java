package com.vecindapp.service;

import com.vecindapp.entity.Calificacion;
import com.vecindapp.entity.Usuario;
import com.vecindapp.repository.dao.IUsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    IUsuarioDAO usudao;

    @Override
    public Usuario findById(int id) {
        return usudao.findById(id);
    }

    @Override
    public List<Usuario> ListUsuarios() {
        return usudao.ListUsuarios();
    }

    @Override
    public Usuario insertUsuario(Usuario usuario) {
        return usudao.insertUsuario(usuario);
    }

    @Override
    public Usuario updateUsuario(Usuario usuario) {
        return usudao.updateUsuario(usuario);
    }

    @Override
    public List<Usuario> findByNombre(String nombre) {
        return usudao.findByNombre(nombre);
    }

   /* @Override
    public List<Usuario> findByCalificacion(Calificacion calificaciones) {
        return usudao.findByCalificacion(calificaciones);
    }*/

    /*@Override
    public Usuario updateByEstado(String estado) {
        return usudao.updateByEstado(estado);
    }*/
}
