package com.vecindapp.repository.dao;

import com.vecindapp.entity.Localidad;

import java.util.List;

public interface ILocalidadDAO {
    public List<Localidad> ListLocalidades();
    public Localidad findById(int id);
    public List<Localidad> findByNombreLocalidad(String nombre);
}
