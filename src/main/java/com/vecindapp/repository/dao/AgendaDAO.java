package com.vecindapp.repository.dao;

import com.vecindapp.entity.Agenda;
import com.vecindapp.repository.jpa.IAgendaJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public class AgendaDAO implements IAgendaDAO{

    @Autowired
    IAgendaJPA agendaJPA;

    @Override
    public List<Agenda> InsertAgenda(Agenda agenda) {
        agendaJPA.save(agenda);
        return listAgenda();
    }

    @Override
    public Agenda UpdateAgenda(Agenda agenda) {
        return agendaJPA.save(agenda);
    }

    @Override
    public Agenda findIdAgenda(int id) {
        return agendaJPA.findById(id).orElse(null);
    }

    @Override
    public List<Agenda> listAgenda() {
        return agendaJPA.findAll();
    }

    @Override
    public List<Agenda> findByFechaInicio(Instant fechaInicio) {
        return agendaJPA.findByFechaInicio(fechaInicio);
    }

    @Override
    public List<Agenda> findByEvento(String evento) {
        return agendaJPA.findByEvento(evento);
    }

    @Override
    public List<Agenda> findByEstado(String estado) {
        return agendaJPA.findByEstado(estado);
    }
}
