package com.vecindapp.repository.dao;

import com.vecindapp.entity.Ciudad;
import com.vecindapp.repository.jpa.ICiudadJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CiudadDAO implements ICiudadDAO {

    @Autowired
    ICiudadJPA jpa;

    @Override
    public Ciudad findById(int id) {
        return jpa.findById(id).orElse(null);
    }

    @Override
    public List<Ciudad> listCiudades() {
        return jpa.findAll();
    }

    @Override
    public Ciudad findByNombreCiudad(String nombre) {
        return jpa.findByNombreCiudad(nombre);
    }
}
