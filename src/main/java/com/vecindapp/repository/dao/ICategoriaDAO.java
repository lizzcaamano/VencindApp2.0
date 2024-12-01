package com.vecindapp.repository.dao;

import com.vecindapp.entity.Categoria;

import java.util.List;

public interface ICategoriaDAO {

    List<Categoria> InsertCategoria(Categoria categoria);
    Categoria UpdateCategoria(Categoria categoria);
    Categoria findIdCategoria(int id);
    List<Categoria> listCategorias();
    Categoria findNameCategoria(String nombre);
}
