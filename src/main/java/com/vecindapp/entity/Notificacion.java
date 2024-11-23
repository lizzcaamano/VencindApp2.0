package com.vecindapp.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "notificaciones")
public class Notificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notificacion_id", nullable = false)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "tipo", length = 50)
    private String tipo;

    @Lob
    @Column(name = "contenido")
    private String contenido;

    @ColumnDefault("0")
    @Column(name = "leido")
    private Boolean leido;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "fecha")
    private Instant fecha;

    @OneToMany(mappedBy = "notificacion")
    private Set<Agenda> agenda = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Boolean getLeido() {
        return leido;
    }

    public void setLeido(Boolean leido) {
        this.leido = leido;
    }

    public Instant getFecha() {
        return fecha;
    }

    public void setFecha(Instant fecha) {
        this.fecha = fecha;
    }

    public Set<Agenda> getAgenda() {
        return agenda;
    }

    public void setAgenda(Set<Agenda> agenda) {
        this.agenda = agenda;
    }

}