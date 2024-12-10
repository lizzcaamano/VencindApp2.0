package com.vecindapp.repository.jpa;

import com.vecindapp.entity.Pago;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPagoJPA extends JpaRepository<Pago, Integer> {

    Pago findPByMonto(Double monto);
}
