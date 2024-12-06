package com.vecindapp.service;

import com.vecindapp.entity.Chat;
import com.vecindapp.entity.Servicio;
import com.vecindapp.repository.dto.ChatDTO;
import com.vecindapp.repository.dto.ServicioDTO;

import java.util.List;

public interface IChatService {

    ChatDTO obtenerChatConRoles(Integer chatId);
    ChatDTO addChat(Integer usuarioId, Integer trabajadorId, Integer servicioId);
    ChatDTO findByRoomId(String roomId);

}
