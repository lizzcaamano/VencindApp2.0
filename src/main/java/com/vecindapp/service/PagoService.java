package com.vecindapp.service;

import com.vecindapp.entity.Pago;
import com.vecindapp.repository.dao.IPagoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagoService implements IPagoService{

    @Autowired
    IPagoDAO pagoDAO;

    @Override
    public List<Pago> addPago(Pago pago) {
        pagoDAO.InsertPago(pago);
        return listAllPagos() ;
    }

    @Override
    public List<Pago> listAllPagos() {
        return pagoDAO.listPagos();
    }

    @Override
    public Pago findByMonto(Double monto) {
        return pagoDAO.findPByMonto(monto);
    }
}
