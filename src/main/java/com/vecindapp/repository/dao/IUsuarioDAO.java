package com.vecindapp.repository.dao;

import com.vecindapp.entity.Calificacion;
import com.vecindapp.entity.Usuario;
import com.vecindapp.repository.dto.AgendaDTO;
import com.vecindapp.repository.dto.UsuarioDTO;
import com.vecindapp.repository.jpa.IUsuarioJPA;

import java.util.List;

public interface IUsuarioDAO {

    //CRUD NORMAL
    public Usuario findById(int id);
    public List<Usuario> ListUsuarios();
    public List<Usuario> insertUsuario(Usuario usuario);
    public Usuario updateUsuario(Usuario usuario);

    //CONSULTAS ADICIONALES
    public List<Usuario> findByNombre(String nombre);
    //public List<Usuario> findByCalificacion(Calificacion calificaciones);
    //public Usuario updateByEstado(String estado);

}
