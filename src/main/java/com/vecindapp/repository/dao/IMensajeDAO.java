package com.vecindapp.repository.dao;

import com.vecindapp.entity.Mensaje;

import java.util.List;

public interface IMensajeDAO {

     Mensaje findById(int id);
     List<Mensaje> findByChatId(int chatId);
     Mensaje save(Mensaje mensaje);
     List<Mensaje> findAll();
}
