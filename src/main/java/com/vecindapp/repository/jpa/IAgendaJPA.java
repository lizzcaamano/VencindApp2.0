package com.vecindapp.repository.jpa;

import com.vecindapp.entity.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

public interface IAgendaJPA extends JpaRepository<Agenda, Integer> {

    //Busqueda por fecha
    List<Agenda> findByFechaInicio(Instant fechaInicio);

    //Busqueda por evento
    List<Agenda> findByEvento(String evento);

    //Busqueda por Estado
    List<Agenda> findByEstado(String estado);

}
