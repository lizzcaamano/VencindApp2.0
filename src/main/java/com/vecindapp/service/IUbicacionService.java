package com.vecindapp.service;

import com.vecindapp.entity.Ubicacion;
import com.vecindapp.repository.dto.UbicacionDTO;

import java.util.List;

public interface IUbicacionService {
    public UbicacionDTO findById(int id);
    public List<UbicacionDTO> listUbicaciones();
    public UbicacionDTO insertUbicacion(UbicacionDTO ubicaciondto);
    public UbicacionDTO updateUbicacion(UbicacionDTO ubicaciondto);
}
