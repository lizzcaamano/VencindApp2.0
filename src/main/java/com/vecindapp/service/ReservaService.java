package com.vecindapp.service;

import com.vecindapp.entity.*;
import com.vecindapp.repository.dao.*;
import com.vecindapp.repository.dto.IReservaMapper;
import com.vecindapp.repository.dto.ReservaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservaService implements IReservaService{

    @Autowired
    IReservaDAO reservaDAO;

    @Autowired
    IReservaMapper reservaMapper;

    @Autowired
    IUsuarioDAO usuarioDAO;

    @Autowired
    IServicioDAO servicioDAO;

    @Autowired
    IPagoDAO pagoDAO;

    @Override
    public List<ReservaDTO> addReserva(ReservaDTO reservaDTO) {

        Reserva reserva = reservaMapper.toEntity(reservaDTO);

        Usuario usuario = usuarioDAO.findById(reservaDTO.getUsuarioId());
        Servicio servicio = servicioDAO.findIdServicio(reservaDTO.getServicioId());
        Pago pago = pagoDAO.findIdPago(reservaDTO.getPagoId());

        reserva.setUsuario(usuario);
        reserva.setServicio(servicio);
        reserva.setPago(pago);

        reservaDAO.InsertReserva(reserva);

        return listAllReservas();
    }

    @Override
    public ReservaDTO UpdReserva(Integer id, ReservaDTO reservaDTO) {
        return null;
    }

    @Override
    public Reserva findIdReserva(int id) {
        return null;
    }

    @Override
    public List<ReservaDTO> listAllReservas() {

        List<Reserva> reservas = reservaDAO.listReservas();

        List<ReservaDTO> reservaDTOS = reservas.stream().
                map(reservaMapper::toDTO).collect(Collectors.toList());

        return reservaDTOS;
    }
}
