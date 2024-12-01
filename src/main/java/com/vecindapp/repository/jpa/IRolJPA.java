package com.vecindapp.repository.jpa;

import com.vecindapp.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IRolJPA extends JpaRepository<Rol, Integer> {
    public Rol findByNombre(String nombre);
}
