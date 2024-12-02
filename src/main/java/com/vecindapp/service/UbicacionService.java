package com.vecindapp.service;

import com.vecindapp.entity.Ubicacion;
import com.vecindapp.repository.dao.IBarrioDAO;
import com.vecindapp.repository.dao.ICiudadDAO;
import com.vecindapp.repository.dao.ILocalidadDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UbicacionService implements IUbicacionService {

    //Para insertar directamente en las entidades
    @Autowired
    IBarrioDAO barriodao;
    @Autowired
    ILocalidadDAO localidaddao;
    @Autowired
    ICiudadDAO ciudaddao;

    @Override
    public Ubicacion findById(int id) {

        return null;
    }

    @Override
    public List<Ubicacion> listUbicaciones() {
        return List.of();
    }

    @Override
    public Ubicacion insertUbicacion(Ubicacion ubicacion) {
        return null;
    }

    @Override
    public Ubicacion updateUbicacion(Ubicacion ubicacion) {
        return null;
    }
}
