package com.vecindapp.repository.dto;

import java.util.List;

public class ChatMensajeDTO {

    private ChatDTO chat;
    private List<MensajeDTO> mensajes;

    // Getters y Setters
    public ChatDTO getChat() {
        return chat;
    }

    public void setChat(ChatDTO chat) {
        this.chat = chat;
    }

    public List<MensajeDTO> getMensajes() {
        return mensajes;
    }

    public void setMensajes(List<MensajeDTO> mensajes) {
        this.mensajes = mensajes;
    }

}
