package com.vecindapp.service;

import com.vecindapp.entity.Servicio;
import com.vecindapp.repository.dto.ServicioDTO;

import java.util.List;

public interface IServicioService {

    List<ServicioDTO> addServicio(ServicioDTO servicioDTO);
    ServicioDTO UpdServicio(Integer id, ServicioDTO servicioDTO);
    Servicio FindIdServicio (int id);
    List<ServicioDTO> listAllServicios();
}
