package com.vecindapp.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Entity
@Table(name = "favoritos")
public class Favorito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favorito_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Usuario cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "trabajador_id")
    private Usuario trabajador;

    @Column(name = "servicio_id")
    private Integer servicioId;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "fecha_agregado")
    private Instant fechaAgregado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public Usuario getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(Usuario trabajador) {
        this.trabajador = trabajador;
    }

    public Integer getServicioId() {
        return servicioId;
    }

    public void setServicioId(Integer servicioId) {
        this.servicioId = servicioId;
    }

    public Instant getFechaAgregado() {
        return fechaAgregado;
    }

    public void setFechaAgregado(Instant fechaAgregado) {
        this.fechaAgregado = fechaAgregado;
    }

}