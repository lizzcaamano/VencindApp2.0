package com.vecindapp.repository.dto;

import java.time.Instant;

public class MensajeDTO {

    private Integer id;
    private String contenido;
    private Instant fechaEnvio;
    private Integer chatId;
    private String senderRole;
    private String remitente;
    private Double price;
    private Boolean esSolicitudPrecio;
    private Integer reservaId;

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Instant getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Instant fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public Integer getChatId() {
        return chatId;
    }

    public void setChatId(Integer chatId) {
        this.chatId = chatId;
    }

    public String getSenderRole() {
        return senderRole;
    }

    public void setSenderRole(String senderRole) {
        this.senderRole = senderRole;
    }

    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getEsSolicitudPrecio() {
        return esSolicitudPrecio;
    }

    public void setEsSolicitudPrecio(Boolean esSolicitudPrecio) {
        this.esSolicitudPrecio = esSolicitudPrecio;
    }

    public Integer getReservaId() {
        return reservaId;
    }

    public void setReservaId(Integer reservaId) {
        this.reservaId = reservaId;
    }
}
