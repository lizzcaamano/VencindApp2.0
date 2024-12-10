package com.vecindapp.service;

import com.vecindapp.entity.Pago;


import java.util.List;

public interface IPagoService {

    List<Pago> addPago(Pago pago);
    List<Pago> listAllPagos();
    Pago findByMonto(Double monto);
}
