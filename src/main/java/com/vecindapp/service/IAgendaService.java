package com.vecindapp.service;

import com.vecindapp.entity.Agenda;

import java.time.Instant;
import java.util.List;

public interface IAgendaService {

    List<Agenda> addAgenda(Agenda agenda);
    Agenda UpdAgenda(Agenda agenda);
    Agenda FindIdAgenda (int id);
    List<Agenda> listAllAgendas();
    List<Agenda> FindByFechaInicio(Instant fechaInicio);
    List<Agenda> FindByEvento(String evento);
    List<Agenda> FindByEstado(String estado);
}
