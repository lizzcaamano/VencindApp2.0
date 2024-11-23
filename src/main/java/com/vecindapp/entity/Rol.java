package com.vecindapp.entity;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "descripcion", length = 500)
    private String descripcion;

    @OneToMany(mappedBy = "role")
    private Set<UsuarioRol> usuariosRoles = new LinkedHashSet<>();

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Set<UsuarioRol> getUsuariosRoles() {
        return usuariosRoles;
    }

    public void setUsuariosRoles(Set<UsuarioRol> usuariosRoles) {
        this.usuariosRoles = usuariosRoles;
    }

}