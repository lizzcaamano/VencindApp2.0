package com.vecindapp.repository.jpa;

import com.vecindapp.entity.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IMensajeJPA extends JpaRepository<Mensaje, Integer> {

    List<Mensaje> findByChatId(int chatId);
}
