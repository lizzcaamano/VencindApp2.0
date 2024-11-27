package com.vecindapp.service;

import com.vecindapp.entity.Calificacion;
import com.vecindapp.entity.Usuario;
import com.vecindapp.repository.dto.UsuarioDTO;

import java.util.List;

public interface IUsuarioService {
    public UsuarioDTO findById(int id);
    public List<UsuarioDTO> ListUsuarios();
    public List<UsuarioDTO> insertUsuario(UsuarioDTO usuariodto);
    public UsuarioDTO updateUsuario(UsuarioDTO usuariodto);

    //CONSULTAS ADICIONALES
    public List<Usuario> findByNombre(String nombre);
    //public List<Usuario> findByCalificacion(Calificacion calificaciones);
    //public Usuario updateByEstado(String estado);

}
