package com.vecindapp.repository.jpa;

import com.vecindapp.entity.Calificacion;
import com.vecindapp.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface IUsuarioJPA extends JpaRepository<Usuario, Integer> {

    //Buscar usuario por Nombre
    public List<Usuario> findByNombre(String nombre);

    Usuario findUsuarioByEmail(String email);

    //TODO: Revisar este metodo
    //Buscar Usuario por Calificación
    //public List<Usuario> findByCalificacion(Calificacion calificaciones);

    // Modificar Estado

    @Modifying
    @Transactional
    @Query("UPDATE Usuario u SET u.estado = :estado WHERE u.id = :id")
    //Int porque devuelve la respuesta de la consulta (si fue exitosa 1 sino 0)
    public int updateEstado(@Param("id") Integer id, @Param("estado") String estado);




    //Buscar usuario por Servicio
    //TODO: Evaluar este método
}
