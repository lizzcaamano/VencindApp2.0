package com.vecindapp.repository.jpa;

import com.vecindapp.entity.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICiudadJPA extends JpaRepository<Ciudad, Integer> {

    //public Ciudad findByNombre(String nombre);
}
