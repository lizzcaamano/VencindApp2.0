package com.vecindapp.repository.dto;

import java.time.Instant;
import java.util.List;

//Heredamos de UsuarioDTO sus atributos y métodos
public class TrabajadorDTO {

    //Atributos propios de la entidad Usuario
    private Integer id;
    private String nombre;
    private String email;
    private String password;
    private String telefono;
    private String descripcion;
    private String foto;
    private Instant fechaRegistro;
    private String estado;


    //Atributos referentes a relaciones que tiene Usuario
    private Integer rolId;
    private String ubicaciondir;
    private String nombreBarrio;
    private String nombreLocalidad;
    private String nombreCiudad;
    private List<String> archivoDocumento;
    private List<String> tipoDocumento;


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

    public Integer getRolId() {
        return rolId;
    }

    public void setRolId(Integer rolId) {
        this.rolId = 2;
    }

    public String getUbicaciondir() {
        return ubicaciondir;
    }

    public void setUbicaciondir(String ubicaciondir) {
        this.ubicaciondir = ubicaciondir;
    }

    public String getNombreBarrio() {
        return nombreBarrio;
    }

    public void setNombreBarrio(String nombreBarrio) {
        this.nombreBarrio = nombreBarrio;
    }

    public String getNombreCiudad() {
        return nombreCiudad;
    }

    public void setNombreCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }

    public String getNombreLocalidad() {
        return nombreLocalidad;
    }

    public void setNombreLocalidad(String nombreLocalidad) {
        this.nombreLocalidad = nombreLocalidad;
    }


}
