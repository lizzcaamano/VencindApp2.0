package com.vecindapp.repository.jpa;

import com.vecindapp.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoriaJPA extends JpaRepository<Categoria, Integer> {

    //Buscar categoria por Nombre
    Categoria findCategoriaByNombre(String nombre);
}
