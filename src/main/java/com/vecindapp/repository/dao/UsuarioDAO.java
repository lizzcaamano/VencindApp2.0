package com.vecindapp.repository.dao;

import com.vecindapp.entity.Calificacion;
import com.vecindapp.entity.Usuario;
import com.vecindapp.repository.jpa.IUsuarioJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsuarioDAO implements IUsuarioDAO{

    @Autowired
    IUsuarioJPA usujpa;

    @Override
    public Usuario findById(int id) {

        return usujpa.findById(id).orElse(null);
    }

    @Override
    public List<Usuario> ListUsuarios() {

        return usujpa.findAll();
    }

    @Override
    public Usuario insertUsuario(Usuario usuario) {

        return usujpa.save(usuario);
    }

    //TODO: pendiente actualizar usuario
    @Override
    public Usuario updateUsuario(Usuario usuario) {
        return null;
    }

    @Override
    public List<Usuario> findByNombre(String nombre) {
        return usujpa.findByNombre(nombre);
    }

    /*@Override
    public List<Usuario> findByCalificacion(Calificacion calificaciones) {
        return usujpa.findByCalificacion(calificaciones);
    }*/

    //TODO: pendiente crear actualizar por estado
    /*@Override
    public Usuario updateByEstado(String estado) {
        return usujpa.updateByEstado(estado);
    }*/
}
