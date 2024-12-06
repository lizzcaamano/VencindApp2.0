package com.vecindapp.repository.jpa;

import com.vecindapp.entity.UsuarioRol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IUsuarioRolJPA extends JpaRepository<UsuarioRol, Integer> {
    public List<UsuarioRol> findByUsuarioId(Integer usuarioid);
    public List<UsuarioRol> findByRoleId(Integer rolid);
   /* @Query("SELECT r.nombre FROM UsuarioRol ur JOIN ur.usuario u JOIN ur.role r WHERE u.id = :userId")*/
   @Query("SELECT r.nombre " +
           "FROM Usuario u " +
           "JOIN u.usuarioRols ur " +
           "JOIN ur.role r " +
           "WHERE u.id = :userId")
    public List<String> findUsuarioRolByUsuarioId(@Param("userId") Integer usuarioId);
}
