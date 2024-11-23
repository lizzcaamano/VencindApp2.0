package com.vecindapp.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Entity
@Table(name = "chat")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonManagedReference
    @JoinColumn(name = "user_id")
    private Usuario usuario;

    @Column(name = "servicio_id")
    private Integer servicioId;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "fecha_inicio")
    private Instant fechaInicio;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Integer getServicioId() {
        return servicioId;
    }

    public void setServicioId(Integer servicioId) {
        this.servicioId = servicioId;
    }

    public Instant getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Instant fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

}