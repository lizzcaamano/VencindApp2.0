package com.vecindapp.repository.dao;

import com.vecindapp.entity.UsuarioRol;
import com.vecindapp.repository.jpa.IUsuarioRolJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsuarioRolDAO implements IUsuarioRolDAO {

    @Autowired
    IUsuarioRolJPA jpa;

    @Override
    public UsuarioRol findById(Integer id) {
        return jpa.findById(id).orElse(null);
    }

    @Override
    public List<UsuarioRol> listUsuariosRol() {
        return jpa.findAll();
    }

    @Override
    public List<UsuarioRol> insertUsuarioRol(UsuarioRol usuarioRol) {
        jpa.save(usuarioRol);
        return listUsuariosRol();
    }

    @Override
    public List<UsuarioRol> findByUsuario(Integer usuarioid) {
        return jpa.findByUsuarioId(usuarioid);
    }

    @Override
    public List<UsuarioRol> findByRole(Integer rolid) {
        return jpa.findByRoleId(rolid);
    }
}
