package com.vecindapp.repository.dao;

import com.vecindapp.entity.Barrio;
import com.vecindapp.repository.jpa.IBarrioJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BarrioDAO implements IBarrioDAO{

    @Autowired
    IBarrioJPA jpa;

    @Override
    public Barrio findById(int id) {
        return jpa.findById(id).orElse(null);
    }

    @Override
    public List<Barrio> ListBarrios() {
        return jpa.findAll();
    }

    @Override
    public List<Barrio> insertBarrio(Barrio barrio) {
        jpa.save(barrio);
        return ListBarrios();
    }

    @Override
    public Barrio findByNombreBarrio(String nombre) {
        return jpa.findByNombreBarrio(nombre);
    }
}
