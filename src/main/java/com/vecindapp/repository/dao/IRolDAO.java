package com.vecindapp.repository.dao;

import com.vecindapp.entity.Localidad;
import com.vecindapp.entity.Rol;

import java.util.List;

public interface IRolDAO {
    public List<Rol> ListRoles();
    public Rol findById(int id);
    public List<Rol> findByNombre(String nombre);
}
