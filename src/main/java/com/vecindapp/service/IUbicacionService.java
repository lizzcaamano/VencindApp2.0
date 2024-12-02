package com.vecindapp.service;

import com.vecindapp.entity.Ubicacion;

import java.util.List;

public interface IUbicacionService {
    public Ubicacion findById(int id);
    public List<Ubicacion> listUbicaciones();
    public Ubicacion insertUbicacion(Ubicacion ubicacion);
    public Ubicacion updateUbicacion(Ubicacion ubicacion);
}
