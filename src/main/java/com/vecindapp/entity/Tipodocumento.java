package com.vecindapp.entity;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "tipodocumento")
public class Tipodocumento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tipo_documento_id", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @OneToMany(mappedBy = "tipoDocumento")
    private Set<Documento> documentos = new LinkedHashSet<>();

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

    public Set<Documento> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(Set<Documento> documentos) {
        this.documentos = documentos;
    }

}