package com.vecindapp.service;


import com.vecindapp.repository.dto.UsuarioDTO;


import java.util.List;


public interface IUsuarioService {
    public UsuarioDTO findById(int id);
    public List<UsuarioDTO> ListUsuarios();
    public List<UsuarioDTO> insertUsuario(UsuarioDTO usuariodto);
    public UsuarioDTO updateUsuario(Integer id, UsuarioDTO usuariodto);

    //CONSULTAS ADICIONALES
    public List<UsuarioDTO> findByNombre(String nombre);
    //public List<Usuario> findByCalificacion(Calificacion calificaciones);
    //public Usuario updateByEstado(String estado);

}
