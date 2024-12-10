package com.vecindapp.service;

import com.vecindapp.entity.Reserva;
import com.vecindapp.repository.dto.ReservaDTO;

import java.util.List;

public interface IReservaService {

    List<ReservaDTO> addReserva(ReservaDTO reservaDTO);
    ReservaDTO UpdReserva(Integer id, ReservaDTO reservaDTO);
    Reserva findIdReserva (int id);
    List<ReservaDTO> listAllReservas();
}
