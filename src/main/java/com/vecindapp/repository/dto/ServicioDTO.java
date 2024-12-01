package com.vecindapp.repository.dto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ServicioDTO {

    @NotNull(message = "El nombre no puede ser nulo")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    private String nombre;

    @Size(min = 50, max = 255, message = "La descripcion debe tener entre 50 y 255 caracteres")
    @NotNull(message = "La descripcion no puede ser nulo")
    private String descripcion;

    @NotNull(message = "La categoria no puede ser nula")
    private String categoriaName;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoriaName() {
        return categoriaName;
    }

    public void setCategoriaName(String categoriaName) {
        this.categoriaName = categoriaName;
    }
}
