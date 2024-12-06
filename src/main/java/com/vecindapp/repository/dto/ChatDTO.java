package com.vecindapp.repository.dto;

import java.time.Instant;
import java.util.List;

public class ChatDTO {

    private Integer chatId;
    private String roomId;
    private Integer servicioId;
    private Integer clienteId;
    private String clienteNombre;
    private Integer trabajadorId;
    private String trabajadorNombre;
    private String clienteRol;  // Aquí agregamos los campos para los roles
    private String trabajadorRol;
    private Instant fechaInicio;
    private List<MensajeDTO> mensajes;


    public Integer getChatId() {
        return chatId;
    }

    public void setChatId(Integer chatId) {
        this.chatId = chatId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public Integer getServicioId() {
        return servicioId;
    }

    public void setServicioId(Integer servicioId) {
        this.servicioId = servicioId;
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public String getClienteNombre() {
        return clienteNombre;
    }

    public void setClienteNombre(String clienteNombre) {
        this.clienteNombre = clienteNombre;
    }

    public Integer getTrabajadorId() {
        return trabajadorId;
    }

    public void setTrabajadorId(Integer trabajadorId) {
        this.trabajadorId = trabajadorId;
    }

    public String getTrabajadorNombre() {
        return trabajadorNombre;
    }

    public void setTrabajadorNombre(String trabajadorNombre) {
        this.trabajadorNombre = trabajadorNombre;
    }

    public String getClienteRol() {
        return clienteRol;
    }

    public void setClienteRol(String clienteRol) {
        this.clienteRol = clienteRol;
    }

    public String getTrabajadorRol() {
        return trabajadorRol;
    }

    public void setTrabajadorRol(String trabajadorRol) {
        this.trabajadorRol = trabajadorRol;
    }

    public Instant getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Instant fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public List<MensajeDTO> getMensajes() {
        return mensajes;
    }

    public void setMensajes(List<MensajeDTO> mensajes) {
        this.mensajes = mensajes;
    }
}

