package com.vecindapp.repository.dto;

import java.time.Instant;
import java.util.List;

public class ReservaDTO {

    private Integer usuarioId;
    private Integer servicioId;
    private Integer pagoId;
    private Instant fechaReserva;
    private Instant fechaServicio;
    private String estado;

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Integer getServicioId() {
        return servicioId;
    }

    public void setServicioId(Integer servicioId) {
        this.servicioId = servicioId;
    }

    public Integer getPagoId() {
        return pagoId;
    }

    public void setPagoId(Integer pagoId) {
        this.pagoId = pagoId;
    }

    public Instant getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(Instant fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public Instant getFechaServicio() {
        return fechaServicio;
    }

    public void setFechaServicio(Instant fechaServicio) {
        this.fechaServicio = fechaServicio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
