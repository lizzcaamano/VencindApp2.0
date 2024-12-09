package com.vecindapp.controller;


import com.vecindapp.repository.dto.ChatDTO;
import com.vecindapp.service.IChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("chats")
public class ChatController {

    @Autowired
    IChatService chatService;

    // Obtener un chat con sus roles
    @GetMapping("/{chatId}")
    public ResponseEntity<ChatDTO> obtenerChatConRoles(@PathVariable Integer chatId) {

            ChatDTO chatDTO = chatService.obtenerChatConRoles(chatId);
            return ResponseEntity.ok(chatDTO);

    }

    @PostMapping(value="insert", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ChatDTO> crearChat(@RequestParam Integer usuarioId,
                                             @RequestParam Integer trabajadorId,
                                             @RequestParam Integer servicioId) {
        try {
            ChatDTO chatDTO = chatService.addChat(usuarioId, trabajadorId, servicioId);
            return ResponseEntity.ok(chatDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
        
}
