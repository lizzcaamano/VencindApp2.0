package com.vecindapp.repository.dao;

import com.vecindapp.entity.Barrio;

import java.util.List;

public interface IBarrioDAO {
    public Barrio findById(int id);
    public List<Barrio> ListBarrios();
    public List<Barrio> insertBarrio(Barrio barrio);

    public Barrio findByNombreBarrio(String nombre);
}
