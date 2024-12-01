package com.vecindapp.repository.dao;


import com.vecindapp.entity.Ciudad;

import java.util.List;

public interface ICiudadDAO {
    //CRUD Normal (métodos necesarios)

    public Ciudad findById(int id);
    public List<Ciudad> listCiudades();

    //Métodos adicionales
    //public Ciudad findByNombre(String nombre);

}
