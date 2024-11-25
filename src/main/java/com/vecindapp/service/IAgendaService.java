package com.vecindapp.service;

import com.vecindapp.entity.Agenda;
import com.vecindapp.repository.dto.AgendaDTO;

import java.time.Instant;
import java.util.List;

public interface IAgendaService {

    List<AgendaDTO> addAgenda(AgendaDTO agendadto);
    AgendaDTO UpdAgenda(Integer id, AgendaDTO agendaDTO);
    Agenda FindIdAgenda (int id);
    List<Agenda> listAllAgendas();
    List<Agenda> FindByFechaInicio(Instant fechaInicio);
    List<Agenda> FindByEvento(String evento);
    List<Agenda> FindByEstado(String estado);
}
