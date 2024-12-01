package com.vecindapp.repository.dto;

import java.time.Instant;

public class UsuarioDTO {

    //Atributos propios de la entidad Usuario
    private Integer id;
    private String nombre;
    private String email;
    private String password;
    private String telefono;
    private String direccion;
    private String descripcion;
    private String foto;
    private Instant fechaRegistro;
    private String estado;

    //Atributos referentes a relaciones que tiene Usuario
    private Integer ubicacionId;
    private String barriod;
    private Integer localidadId;
    private String localidad;
    private Integer roId;
    private Integer ciudadId;
    private String ciudad;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Instant getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Instant fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getUbicacionId() {
        return ubicacionId;
    }

    public void setUbicacionId(Integer ubicacionId) {
        this.ubicacionId = ubicacionId;
    }

    public String getBarriod() {
        return barriod;
    }

    public void setBarriod(String ciudad) {
        this.barriod = ciudad;
    }

}
