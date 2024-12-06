package com.vecindapp.repository.dao;

import com.vecindapp.entity.UsuarioRol;

import java.util.List;

public interface IUsuarioRolDAO {
    public UsuarioRol findById(Integer id);
    public List<UsuarioRol> listUsuariosRol();
    public List<UsuarioRol> insertUsuarioRol(UsuarioRol usuarioRol);
    public List<UsuarioRol> findByUsuario(Integer usuarioid);
    public List<UsuarioRol> findByRole(Integer rolid);
    public List<String> findUsuarioRolByUsuarioId(Integer usuarioId);
}
