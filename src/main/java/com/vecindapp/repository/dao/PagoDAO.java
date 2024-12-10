package com.vecindapp.repository.dao;

import com.vecindapp.entity.Pago;
import com.vecindapp.repository.jpa.IPagoJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PagoDAO implements IPagoDAO{

    @Autowired
    IPagoJPA pagoJPA;


    @Override
    public List<Pago> InsertPago(Pago pago) {
        pagoJPA.save(pago);
        return listPagos();
    }

    @Override
    public Pago UpdatePago(Pago pago) {
        return pagoJPA.save(pago);
    }

    @Override
    public Pago findIdPago(int id) {
        return pagoJPA.findById(id).orElse(null);
    }

    @Override
    public List<Pago> listPagos() {
        return pagoJPA.findAll();
    }

    @Override
    public Pago findPByMonto(Double monto) {
        return pagoJPA.findPByMonto(monto);
    }
}
