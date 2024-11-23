package com.vecindapp.service;

import com.vecindapp.entity.Usuario;

import java.util.List;

public interface IUsuarioService {
    //CRUD NORMAL
    public Usuario findByID(int id);
    public List<Usuario> ListUsuarios();
    public Usuario insertUsuario(Usuario usuario);
    public Usuario updateUsuario(Usuario usuario);

    //CONSULTAS ADICIONALES
    public List<Usuario> findByNombre(String nombre);
    //public List<Usuario> findByCalificacion( int calificacion);
    //public Usuario actualizarEstado(String estado);
}
