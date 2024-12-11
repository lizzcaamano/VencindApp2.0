package com.vecindapp.repository.jpa;

import com.vecindapp.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface IRolJPA extends JpaRepository<Rol, Integer> {
    public Rol findByNombre(String nombre);
    @Query("SELECT ur.role.id FROM UsuarioRol ur WHERE ur.usuario.id = :usuarioId")
    List<Integer> findRoleIdsByUsuarioId(@Param("usuarioId") Integer usuarioId);
}
