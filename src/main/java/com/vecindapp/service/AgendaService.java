package com.vecindapp.service;

import com.vecindapp.entity.Agenda;
import com.vecindapp.repository.dao.IAgendaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class AgendaService implements IAgendaService {

    @Autowired
    IAgendaDAO agendaDAO;

    @Override
    public List<Agenda> addAgenda(Agenda agenda) {
        return agendaDAO.InsertAgenda(agenda);
    }

    @Override
    public Agenda UpdAgenda(Agenda agenda) {
        return agendaDAO.UpdateAgenda(agenda);
    }

    @Override
    public Agenda FindIdAgenda(int id) {
        return agendaDAO.findIdAgenda(id);
    }

    @Override
    public List<Agenda> listAllAgendas() {
        return agendaDAO.listAgenda();
    }

    @Override
    public List<Agenda> FindByFechaInicio(Instant fechaInicio) {
        return agendaDAO.findByFechaInicio(fechaInicio);
    }

    @Override
    public List<Agenda> FindByEvento(String evento) {
        return agendaDAO.findByEvento(evento);
    }

    @Override
    public List<Agenda> FindByEstado(String estado) {
        return agendaDAO.findByEstado(estado);
    }
}
