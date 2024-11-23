package com.vecindapp.service;

import com.vecindapp.entity.Calificacion;
import com.vecindapp.entity.Usuario;

import java.util.List;

public interface IUsuarioService {
    //CRUD NORMAL
    public Usuario findById(int id);
    public List<Usuario> ListUsuarios();
    public Usuario insertUsuario(Usuario usuario);
    public Usuario updateUsuario(Usuario usuario);

    //CONSULTAS ADICIONALES
    public List<Usuario> findByNombre(String nombre);
    //public List<Usuario> findByCalificacion(Calificacion calificaciones);
    //public Usuario updateByEstado(String estado);
}
