package com.vecindapp.repository.dao;

import com.vecindapp.entity.Mensaje;
import com.vecindapp.repository.jpa.IMensajeJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MensajeDAO implements IMensajeDAO{

    @Autowired
    private IMensajeJPA mensajeJPA;

    @Override
    public Mensaje findById(int id) {
        return mensajeJPA.findById(id).orElse(null);
    }

    @Override
    public List<Mensaje> findByChatId(int chatId) {
        return mensajeJPA.findByChatId(chatId);
    }

    @Override
    public Mensaje save(Mensaje mensaje) {
        return mensajeJPA.save(mensaje);
    }

    @Override
    public List<Mensaje> findAll() {
        return mensajeJPA.findAll();
    }
}
