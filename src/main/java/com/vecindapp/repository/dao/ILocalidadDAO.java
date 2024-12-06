package com.vecindapp.repository.dao;

import com.vecindapp.entity.Localidad;

import java.util.List;
import java.util.Optional;

public interface ILocalidadDAO {
    public List<Localidad> ListLocalidades();
    public Localidad findById(int id);
    public Localidad insert(Localidad localidad);
    public Localidad findByNombreLocalidad(String nombre);
}
