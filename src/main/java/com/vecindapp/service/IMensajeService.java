package com.vecindapp.service;

import com.vecindapp.entity.Mensaje;
import com.vecindapp.repository.dto.MensajeDTO;

import java.util.List;

public interface IMensajeService {

    MensajeDTO guardarMensaje(MensajeDTO mensajeDTO);
    List<MensajeDTO> listMensajesByChat(int chatId);

}
