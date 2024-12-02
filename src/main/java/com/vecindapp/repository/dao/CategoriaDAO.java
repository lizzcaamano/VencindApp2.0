package com.vecindapp.repository.dao;

import com.vecindapp.entity.Categoria;
import com.vecindapp.repository.jpa.ICategoriaJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoriaDAO implements ICategoriaDAO{

    @Autowired
    ICategoriaJPA categoriaJPA;

    @Override
    public List<Categoria> InsertCategoria(Categoria categoria) {
        categoriaJPA.save(categoria);
        return listCategorias();
    }

    @Override
    public Categoria UpdateCategoria(Categoria categoria) {
        return categoriaJPA.save(categoria);
    }

    @Override
    public Categoria findIdCategoria(int id) {
        return categoriaJPA.findById(id).orElse(null);
    }

    @Override
    public List<Categoria> listCategorias() {
        return categoriaJPA.findAll();
    }

    @Override
    public Categoria findNameCategoria(String nombre) {
        return categoriaJPA.findCategoriaByNombre(nombre);
    }
}
