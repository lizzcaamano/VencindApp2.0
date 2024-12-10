package com.vecindapp.repository.jpa;

import com.vecindapp.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IReservaJPA extends JpaRepository<Reserva, Integer> {
}
