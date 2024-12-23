package com.vecindapp.repository.jpa;

import com.vecindapp.entity.Categoria;
import com.vecindapp.entity.Ubicacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUbicacionJPA extends JpaRepository<Ubicacion, Integer> {

    Ubicacion findUbicacionByDireccion(String direccion);
}
