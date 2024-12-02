package com.vecindapp.repository.dto;

public class BarrioDTO {

    private Integer id;
    private String nombreBarrio;
    private LocalidadDTO localidad;

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

    public LocalidadDTO getLocalidad() {
        return localidad;
    }

    public void setLocalidad(LocalidadDTO localidad) {
        this.localidad = localidad;
    }
}
