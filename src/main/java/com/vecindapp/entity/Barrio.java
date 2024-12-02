package com.vecindapp.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "barrio")
public class Barrio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "barrio_id", nullable = false)
    private Integer id;

    @Column(name = "nombre_barrio", length = 150)
    private String nombreBarrio;

    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "localidad_id")
    private Localidad localidad;

    @OneToMany(mappedBy = "barrio")
    private Set<Ubicacion> ubicacions = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreBarrio() {
        return nombreBarrio;
    }

    public void setNombreBarrio(String nombreBarrio) {
        this.nombreBarrio = nombreBarrio;
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

    public Set<Ubicacion> getUbicacions() {
        return ubicacions;
    }

    public void setUbicacions(Set<Ubicacion> ubicacions) {
        this.ubicacions = ubicacions;
    }

}