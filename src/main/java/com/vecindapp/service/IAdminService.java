package com.vecindapp.service;

import com.vecindapp.repository.dto.AdministradorDTO;
import com.vecindapp.repository.dto.ClienteDTO;

import java.util.List;

public interface IAdminService {
    public AdministradorDTO findById(int id);
    public List<AdministradorDTO> ListAdmin();
    public List<AdministradorDTO> insertAdmin(AdministradorDTO administradorDTO);
    public AdministradorDTO updateAdmin(Integer id, AdministradorDTO administradorDTO);

    //CONSULTAS ADICIONALES
    public List<AdministradorDTO> findByNombre(String nombre);
    public AdministradorDTO updateByEstado(int id, String estado);
}
