package com.vecindapp.repository.jpa;

import com.vecindapp.entity.Barrio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBarrioJPA extends JpaRepository<Barrio, Integer> {
    public Barrio findByNombreBarrio(String nombre);
}
