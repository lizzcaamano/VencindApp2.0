package com.vecindapp.service;

import com.vecindapp.repository.dto.ClienteDTO;
import com.vecindapp.repository.dto.TrabajadorDTO;

import java.util.List;

public interface ITrabajadorService {
    public TrabajadorDTO findById(int id);
    public List<TrabajadorDTO> ListTrabajadores();
    public List<TrabajadorDTO> insertTrabajador(TrabajadorDTO trabajadorDTO);
    public TrabajadorDTO updateTrabajador(Integer id, TrabajadorDTO trabajadorDTO);

    //CONSULTAS ADICIONALES
    public List<TrabajadorDTO> findByNombre(String nombre);
    //public List<Usuario> findByCalificacion(Calificacion calificaciones);
    //public Usuario updateByEstado(String estado);
}
