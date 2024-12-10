package com.vecindapp.repository.dao;

import com.vecindapp.entity.Reserva;
import com.vecindapp.repository.jpa.IReservaJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReservaDAO implements IReservaDAO{

    @Autowired
    IReservaJPA reservaJPA;

    @Override
    public List<Reserva> InsertReserva(Reserva reserva) {
        reservaJPA.save(reserva);
        return listReservas();
    }

    @Override
    public Reserva UpdateReserva(Reserva reserva) {
        return reservaJPA.save(reserva);
    }

    @Override
    public Reserva findIdReserva(int id) {
        return reservaJPA.findById(id).orElse(null);
    }

    @Override
    public List<Reserva> listReservas() {
        return reservaJPA.findAll();
    }
}
