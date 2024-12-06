package com.vecindapp.controller;

import com.vecindapp.repository.dao.IChatDAO;
import com.vecindapp.repository.dto.MensajeDTO;
import com.vecindapp.service.IChatService;
import com.vecindapp.service.IMensajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin("*")
public class WebSocketController {

    @Autowired
    private IMensajeService mensajeService;

    @Autowired
    private IChatService chatService;

    @Autowired
    IChatDAO chatDAO;

    @MessageMapping("/chat/{roomId}/send") // Cliente envía a /app/chat/{roomId}/send
    @SendTo("/topic/room/{roomId}") // Este mensaje será enviado a todos los suscriptores de /topic/room/{roomId}
    public MensajeDTO enviarMensaje(@DestinationVariable String roomId, MensajeDTO mensajeDTO) {

        // Validar que el roomId corresponde a un chat existente
        if (chatDAO.findChatByRoomId((roomId)) == null) {
            new RuntimeException("Room no encontrada");
        }

        // Guardar el mensaje utilizando el servicio de mensajes
        mensajeDTO = mensajeService.guardarMensaje(mensajeDTO);

        // Retornar el mensaje guardado para que sea enviado a los clientes suscritos a la sala
        return mensajeDTO;
    }


    @GetMapping("/{chatId}/mensajes")
    public ResponseEntity<List<MensajeDTO>> obtenerMensajes(@PathVariable Integer chatId) {
        List<MensajeDTO> mensajesDTO = mensajeService.listMensajesByChat(chatId);
        return ResponseEntity.ok(mensajesDTO);
    }
}
