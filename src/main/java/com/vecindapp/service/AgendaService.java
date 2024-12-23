package com.vecindapp.service;

import com.vecindapp.entity.Agenda;
import com.vecindapp.entity.Notificacion;
import com.vecindapp.entity.Usuario;
import com.vecindapp.repository.dao.IAgendaDAO;
import com.vecindapp.repository.dto.AgendaDTO;
import com.vecindapp.repository.dto.IAgendaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgendaService implements IAgendaService {

    @Autowired
    IAgendaDAO agendaDAO;

    @Autowired
    IAgendaMapper agendaMapper;

    @Override
    public List<AgendaDTO> addAgenda(AgendaDTO agendadto) {
        //Mapear DTO a Entidad;
        Agenda agenda = agendaMapper.toEntity(agendadto);

        // Asignar la notificación y el usuario si se pasan los IDs
        if (agendadto.getNotificacionId() != null) {
            Notificacion notificacion = new Notificacion();
            notificacion.setId(agendadto.getNotificacionId());
            agenda.setNotificacion(notificacion);
        }

        if (agendadto.getUsuarioId() != null) {
            Usuario usuario = new Usuario();
            usuario.setId(agendadto.getUsuarioId());
            agenda.setUsuario(usuario);
        }

        agendaDAO.InsertAgenda(agenda);

        //obtener la lista de agendas
        List<Agenda> allAgendas = agendaDAO.listAgenda();

        return allAgendas.stream().map(agendaMapper::toDTO).collect(Collectors.toList());
    }


    @Override
    public AgendaDTO UpdAgenda(Integer id, AgendaDTO agendaDTO) {
        // Buscar la entidad existente por ID
        Agenda existeAgenda = agendaDAO.findIdAgenda(id);
        if (existeAgenda == null) {
            throw new RuntimeException("Agenda no encontrada");
        }

        // Mapear los datos actualizados desde el DTO a la entidad existente
        existeAgenda.setFechaInicio(agendaDTO.getFechaInicio());
        existeAgenda.setEvento(agendaDTO.getEvento());
        existeAgenda.setEstado(agendaDTO.getEstado());
        existeAgenda.setUsuario(existeAgenda.getUsuario());
        existeAgenda.setNotificacion(existeAgenda.getNotificacion());

        // Guardar los cambios en la base de datos
        Agenda updatedAgenda = agendaDAO.UpdateAgenda(existeAgenda);

        // Mapear la entidad actualizada de vuelta a un DTO
        return agendaMapper.toDTO(updatedAgenda);
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
