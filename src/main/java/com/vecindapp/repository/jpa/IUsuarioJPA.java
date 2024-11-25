package com.vecindapp.repository.jpa;

import com.vecindapp.entity.Calificacion;
import com.vecindapp.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IUsuarioJPA extends JpaRepository<Usuario, Integer> {

    //Buscar usuario por Nombre
    public List<Usuario> findByNombre(String nombre);

    //TODO: Revisar este metodo
    //Buscar Usuario por Calificación
    //public List<Usuario> findByCalificacion(Calificacion calificaciones);

    // Modificar Estado
    //public Usuario updateByEstado(String estado);



    //Buscar usuario por Servicio
    //TODO: Evaluar este método
}
