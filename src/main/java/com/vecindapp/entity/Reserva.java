package com.vecindapp.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "reserva")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reserva_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonManagedReference
    @JoinColumn(name = "cliente_id")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "servicio_id")
    private Servicio servicio;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "pago_id")
    private Pago pago;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "fecha_reserva")
    private Instant fechaReserva;

    @Column(name = "fecha_servicio")
    private Instant fechaServicio;

    @ColumnDefault("'pendiente'")
    @Lob
    @Column(name = "estado")
    private String estado;

    @OneToMany(mappedBy = "reserva")
    private Set<Reporte> reportes = new LinkedHashSet<>();

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

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
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

    public Set<Reporte> getReportes() {
        return reportes;
    }

    public void setReportes(Set<Reporte> reportes) {
        this.reportes = reportes;
    }

}