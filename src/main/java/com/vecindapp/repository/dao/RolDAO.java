package com.vecindapp.repository.dao;

import com.vecindapp.entity.Rol;
import com.vecindapp.repository.jpa.IRolJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RolDAO implements IRolDAO {

    @Autowired
    IRolJPA jpa;

    @Override
    public List<Rol> ListRoles() {
        return jpa.findAll();
    }

    @Override
    public Rol findById(int id) {
        return jpa.findById(id).orElse(null);
    }

    @Override
    public List<Rol> findByNombre(String nombre) {
        jpa.findByNombre(nombre);
        return ListRoles();
    }

    @Override
    public List<Integer> findRolByUsuarioId(int id) {
        return jpa.findRoleIdsByUsuarioId(id);
    }
}
