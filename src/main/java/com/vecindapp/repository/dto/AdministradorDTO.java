package com.vecindapp.repository.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.Instant;

public class AdministradorDTO {

    //Atributos propios de la entidad Usuario
    // Atributos propios de la entidad Usuario
    @NotNull(message = "El ID no puede ser nulo")
    private Integer id;

    @NotNull(message = "El nombre no puede ser nulo")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    @Pattern(regexp = "^[A-Za-záéíóúÁÉÍÓÚñÑ ]+$", message = "Formato invalido, ingrese su nombre correctamente")
    private String nombre;

    @NotNull(message = "El correo electrónico no puede ser nulo")
    @Email(message = "El correo electrónico debe ser válido")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@vecindapp\\.com$", message = "El correo electrónico debe pertenecer a 'vecindapp.com'")
    private String email;

    @NotNull(message = "La contraseña no puede ser nula")
    @Size(min = 6, max = 20, message = "La contraseña debe tener entre 6 y 20 caracteres")
    private String password;

    @NotNull(message = "El teléfono no puede ser nulo")
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "El teléfono debe ser un número válido (10-15 dígitos)")
    private String telefono;

    private Instant fechaRegistro;

    @NotNull(message = "El estado no puede ser nulo")
    @Pattern(regexp = "^(activo|inactivo|suspendido|pendiente)$", message = "El estado es invalido")
    private String estado;

    //Atributos referentes a relaciones que tiene Usuario
    @NotNull(message = "El ID del rol no puede ser nulo")
    private Integer rolId;

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
        this.rolId = 1;
    }
}
