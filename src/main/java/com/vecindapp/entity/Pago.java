package com.vecindapp.entity;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "pago")
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pago_id", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "tipo_pago")
    private String tipoPago;

    @Column(name = "fecha_pago")
    private Instant fechaPago;

    @Column(name = "monto")
    private Double monto;

    @Lob
    @Column(name = "estado")
    private String estado;

    @OneToMany(mappedBy = "pago")
    private Set<Reserva> reservas = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public Instant getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Instant fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Set<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(Set<Reserva> reservas) {
        this.reservas = reservas;
    }

}