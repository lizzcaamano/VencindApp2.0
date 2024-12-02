package com.vecindapp.repository.dto;

public class UbicacionDTO {

    private Integer id;
    private String direccion;

    //Atributos de relaciones
    private BarrioDTO barrio;


    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public BarrioDTO getBarrio() {
        return barrio;
    }

    public void setBarrio(BarrioDTO barrio) {
        this.barrio = barrio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
