package com.vecindapp.repository.dto;

import com.vecindapp.entity.Barrio;
import com.vecindapp.entity.Ubicacion;

import java.time.Instant;

//Heredamos de UsuarioDTO sus atributos y métodos
public class ClienteDTO{

    //Atributos propios de la entidad Usuario
    private Integer id;
    private String nombre;
    private String email;
    private String password;
    private String telefono;
    private String foto;
    private Instant fechaRegistro;
    private String estado;


    //Atributos referentes a relaciones que tiene Usuario
    private String rolName;
    private String ubicacionName;
    private String barrioName;
    private String localidadName;
    private String ciudadName;


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

    public String getRolName() {
        return rolName;
    }

    public void setRolName(String rolName) {
        this.rolName = rolName;
    }

    public String getUbicacionName() {
        return ubicacionName;
    }

    public void setUbicacionName(String ubicacionName) {
        this.ubicacionName = ubicacionName;
    }

    public String getBarrioName() {
        return barrioName;
    }

    public void setBarrioName(String barrioName) {
        this.barrioName = barrioName;
    }

    public String getLocalidadName() {
        return localidadName;
    }

    public void setLocalidadName(String localidadName) {
        this.localidadName = localidadName;
    }

    public String getCiudadName() {
        return ciudadName;
    }

    public void setCiudadName(String ciudadName) {
        this.ciudadName = ciudadName;
    }
}
