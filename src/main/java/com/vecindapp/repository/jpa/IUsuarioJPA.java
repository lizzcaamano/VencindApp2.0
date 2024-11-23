package com.vecindapp.repository.jpa;

import com.vecindapp.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IUsuarioJPA extends JpaRepository<Usuario, Integer> {

    //Buscar usuario por Nombre
    public List<Usuario> findByNombre(String nombre);

    //Buscar Usuario por Calificación
    //public List<Usuario> findByCalificacion( int calificacion);

    // Modificar Estado
    //public Usuario actualizarEstado(String estado);

    List<Usuario> id(Integer id);

    //Buscar usuario por Servicio
    //TODO: Evaluar este método
}
