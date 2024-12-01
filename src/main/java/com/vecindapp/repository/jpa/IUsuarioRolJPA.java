package com.vecindapp.repository.jpa;

import com.vecindapp.entity.UsuarioRol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IUsuarioRolJPA extends JpaRepository<UsuarioRol, Integer> {
    public List<UsuarioRol> findByUsuarioId(Integer usuarioid);
    public List<UsuarioRol> findByRoleId(Integer rolid);
}
