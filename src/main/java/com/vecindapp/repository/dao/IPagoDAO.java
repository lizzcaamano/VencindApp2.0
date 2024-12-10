package com.vecindapp.repository.dao;

import com.vecindapp.entity.Pago;

import java.util.List;

public interface IPagoDAO {

    List<Pago> InsertPago(Pago pago);
    Pago UpdatePago(Pago pago);
    Pago findIdPago(int id);
    List<Pago> listPagos();
    Pago findPByMonto(Double monto);
}
