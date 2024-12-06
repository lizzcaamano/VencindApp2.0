package com.vecindapp.repository.dto;

public class UbicacionDTO {

    private Integer id;
    private String direccion;
    private String barrionom;
    private String nombreLocalidad;
    private String ciudad;

    //Atributos de relaciones
    //private BarrioDTO barrio;


    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /*
    public BarrioDTO getBarrio() {
        return barrio;
    }

    public void setBarrio(BarrioDTO barrio) {
        this.barrio = barrio;
    }
    */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBarrionom() {
        return barrionom;
    }

    public void setBarrionom(String barrionom) {
        this.barrionom = barrionom;
    }

    public String getNombreLocalidad() {
        return nombreLocalidad;
    }

    public void setNombreLocalidad(String nombreLocalidad) {
        this.nombreLocalidad = nombreLocalidad;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
}
