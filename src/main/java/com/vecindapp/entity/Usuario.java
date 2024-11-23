package com.vecindapp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.*;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "telefono", length = 20)
    private String telefono;

    @Column(name = "direccion")
    private String direccion;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "fecha_registro")
    private Instant fechaRegistro;

    @ColumnDefault("'activo'")
    @Lob
    @Column(name = "estado")
    private String estado;


    @JsonManagedReference
    @OneToMany(mappedBy = "usuario")
    private List<Agenda> agenda = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private List<Calificacion> calificaciones = new ArrayList<>();


    @JsonBackReference
    @OneToMany(mappedBy = "usuario")
    private List<Chat> chats = new ArrayList<>();

    @JsonBackReference
    @OneToMany(mappedBy = "usuario")
    private List<Documento> documentos = new ArrayList<>();

    @JsonBackReference
    @OneToMany(mappedBy = "cliente")
    private List<Favorito> favoritos = new ArrayList<>();

    @JsonBackReference
    @OneToMany(mappedBy = "usuario")
    private List<Reporte> reportes = new ArrayList<>();

    @JsonBackReference
    @OneToMany(mappedBy = "usuario")
    private List<Reserva> reservas = new ArrayList<>();

    @JsonBackReference
    @OneToMany(mappedBy = "usuario")
    private List<UsuarioRol> usuarioRols = new ArrayList<>();

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

    public List<Agenda> getAgenda() {return agenda;}

    public void setAgenda(List<Agenda> agenda) {this.agenda = agenda;}

    public List<Calificacion> getCalificacion() {return calificaciones;}

    public void setCalificacion(List<Calificacion> calificaciones) {this.calificaciones = calificaciones;}

    public List<Chat> getChats() {return chats;}

    public void setChats(List<Chat> chats) {this.chats = chats;}

    public List<Documento> getDocumentos() {return documentos;}

    public void setDocumentos(List<Documento> documentos) {this.documentos = documentos;}

    public List<Favorito> getFavoritos() {return favoritos;}

    public void setFavoritos(List<Favorito> favoritos) {this.favoritos = favoritos;}

    public List<Reporte> getReportes() {return reportes;}

    public void setReportes(List<Reporte> reportes) {this.reportes = reportes;}

    public List<Reserva> getReservas() {return reservas;}

    public void setReservas(List<Reserva> reservas) {this.reservas = reservas;}

    public List<UsuarioRol> getUsuarioRols() {return usuarioRols;}

    public void setUsuarioRols(List<UsuarioRol> usuarioRols) {this.usuarioRols = usuarioRols;}
}