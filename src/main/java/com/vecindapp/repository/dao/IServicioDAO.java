package com.vecindapp.repository.dao;

import com.vecindapp.entity.Servicio;

import java.util.List;

public interface IServicioDAO {

    List<Servicio> InsertServicio(Servicio servicio);
    Servicio UpdateServicio(Servicio servicio);
    Servicio findIdServicio(int id);
    List<Servicio> listServicios();
}
