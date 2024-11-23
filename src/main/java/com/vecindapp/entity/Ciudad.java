package com.vecindapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ciudad")
public class Ciudad {
    @Id
    @Column(name = "ciudad_id", nullable = false)
    private Integer id;

    @Column(name = "nombre_ciudad", length = 150)
    private String nombreCiudad;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreCiudad() {
        return nombreCiudad;
    }

    public void setNombreCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }

}