package com.vecindapp.repository.dao;

import com.vecindapp.entity.Agenda;
import com.vecindapp.repository.jpa.IAgendaJPA;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.List;

public interface IAgendaDAO {

    List<Agenda> InsertAgenda(Agenda agenda);
    Agenda UpdateAgenda(Agenda agenda);
    Agenda findIdAgenda(int id);
    List<Agenda> listAgenda();
    List<Agenda> findByFechaInicio(Instant fechaInicio);
    List<Agenda> findByEvento(String evento);
    List<Agenda> findByEstado(String estado);

}
