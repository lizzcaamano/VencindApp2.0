package com.vecindapp.repository.dao;

import com.vecindapp.entity.Ubicacion;
import com.vecindapp.repository.jpa.IUbicacionJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UbicacionDAO implements IUbicacionDAO {

    @Autowired
    IUbicacionJPA jpa;

    @Override
    public Ubicacion findById(int id) {
        return jpa.findById(id).orElse(null);
    }

    @Override
    public List<Ubicacion> listUbicaciones() {
        return jpa.findAll();
    }

    @Override
    public Ubicacion insertUbicacion(Ubicacion ubicacion) {
        return jpa.save(ubicacion);
    }

    @Override
    public Ubicacion updateUbicacion(Ubicacion ubicacion) {
        return jpa.save(ubicacion);
    }
}
