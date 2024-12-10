package com.vecindapp.repository.dao;

import com.vecindapp.entity.Reserva;

import java.util.List;

public interface IReservaDAO {

    List<Reserva> InsertReserva(Reserva reserva);
    Reserva UpdateReserva(Reserva reserva);
    Reserva findIdReserva(int id);
    List<Reserva> listReservas();
}
