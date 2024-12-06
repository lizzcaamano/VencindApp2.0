package com.vecindapp.repository.dao;

import com.vecindapp.entity.Usuario;
import com.vecindapp.repository.jpa.IUsuarioJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
    public List<Usuario> insertUsuario(Usuario usuario) {
        usujpa.save(usuario);
        return usujpa.findAll();
    }

    @Override
    public Usuario updateUsuario(Usuario usuario) {
        return usujpa.save(usuario);
    }

    @Override
    public List<Usuario> findByNombre(String nombre) {
        return usujpa.findByNombre(nombre);
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return usujpa.findByEmail(email);
    }

    @Override
    public String updateByEstado( int id, String estado) {
        return usujpa.updateEstado(id, estado);
    }


}
