package com.vecindapp.repository.dao;

import com.vecindapp.entity.Localidad;
import com.vecindapp.repository.jpa.ILocalidadJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LocalidadDAO implements ILocalidadDAO {

    @Autowired
    ILocalidadJPA jpa;

    @Override
    public List<Localidad> ListLocalidades() {
        return jpa.findAll();
    }

    @Override
    public Localidad findById(int id) {
        return jpa.findById(id).orElse(null);
    }

    @Override
    public List<Localidad> findByNombreLocalidad(String nombre) {
        jpa.findByNombreLocalidad(nombre);
        return ListLocalidades();
    }
}
