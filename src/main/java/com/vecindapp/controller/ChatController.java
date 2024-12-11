package com.vecindapp.controller;


import com.vecindapp.entity.Chat;
import com.vecindapp.entity.Mensaje;
import com.vecindapp.entity.Pago;
import com.vecindapp.entity.Reserva;
import com.vecindapp.repository.dao.IChatDAO;
import com.vecindapp.repository.dao.IMensajeDAO;
import com.vecindapp.repository.dto.*;
import com.vecindapp.service.IChatService;
import com.vecindapp.service.IMensajeService;
import com.vecindapp.service.IPagoService;
import com.vecindapp.service.IReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController

@RequestMapping("chats")
public class ChatController {

    @Autowired
    IChatService chatService;

    @Autowired
    IMensajeService mensajeService;

    @Autowired
    IPagoService pagoService;

    @Autowired
    IReservaService reservaService;

    @Autowired
    IMensajeDAO mensajeDAO;

    @Autowired
    IChatDAO chatDAO;

    @Autowired
    IReservaMapper reservaMapper;

    @GetMapping("/user-info")
    public String getUserInfo() {
        // Obtén la autenticación del contexto de seguridad
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(username);

        return "hola" + username;
    }

    // Obtener un chat con sus roles
    @GetMapping("/{chatId}")
    public ResponseEntity<ChatDTO> obtenerChatConRoles(@PathVariable Integer chatId) {

        ChatDTO chatDTO = chatService.obtenerChatConRoles(chatId);
        return ResponseEntity.ok(chatDTO);

    }

    @PostMapping(value = "insert", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ChatDTO> crearChat(@RequestBody ChatRequestDTO chatRequest) {
        try {
            // Obtener los valores del objeto recibido
            Integer usuarioId = chatRequest.getUsuarioId();
            Integer trabajadorId = chatRequest.getTrabajadorId();
            Integer servicioId = chatRequest.getServicioId();

            // Llamar al servicio para agregar el chat
            ChatDTO chatDTO = chatService.addChat(usuarioId, trabajadorId, servicioId);
            return ResponseEntity.ok(chatDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

}
