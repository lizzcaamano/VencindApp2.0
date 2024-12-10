package com.vecindapp.repository.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.Instant;
import java.util.List;

//Heredamos de UsuarioDTO sus atributos y métodos
public class TrabajadorDTO {

    // Atributos propios de la entidad Usuario
    @NotNull(message = "El ID no puede ser nulo")
    private Integer id;

    @NotNull(message = "El nombre no puede ser nulo")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    @Pattern(regexp = "^[A-Za-záéíóúÁÉÍÓÚñÑ ]+$", message = "Formato invalido, ingrese su nombre correctamente")
    private String nombre;

    @NotNull(message = "El correo electrónico no puede ser nulo")
    @Email(message = "El correo electrónico debe ser válido")
    private String email;

    @NotNull(message = "La contraseña no puede ser nula")
    @Size(min = 6, max = 20, message = "La contraseña debe tener entre 6 y 20 caracteres")
    private String password;

    @NotNull(message = "El teléfono no puede ser nulo")
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "El teléfono debe ser un número válido (10-15 dígitos)")
    private String telefono;

    @Size(max = 255, message = "La descripción no debe exceder los 255 caracteres")
    private String descripcion;

    @Size(max = 255, message = "La foto no debe exceder los 255 caracteres")
    private String foto;

    private Instant fechaRegistro;

    @NotNull(message = "El estado no puede ser nulo")
    @Pattern(regexp = "^(activo|inactivo|suspendido|pendiente)$", message = "El estado es invalido")
    private String estado;

    // Atributos referentes a relaciones que tiene Usuario
    @NotNull(message = "El ID del rol no puede ser nulo")
    private Integer rolId;

    @Size(max = 255, message = "La dirección de ubicación no debe exceder los 255 caracteres")
    private String ubicaciondir;

    @Size(max = 100, message = "El nombre del barrio no debe exceder los 100 caracteres")
    @Pattern(regexp = "^[A-Za-záéíóúÁÉÍÓÚñÑ ]+$", message = "El barrio solo puede contener letras y espacios")
    private String nombreBarrio;

    @Size(max = 100, message = "El nombre de la localidad no debe exceder los 100 caracteres")
    @Pattern(regexp = "^[A-Za-záéíóúÁÉÍÓÚñÑ ]+$", message = "La localidad solo puede contener letras y espacios")
    private String nombreLocalidad;

    @Size(max = 100, message = "El nombre de la ciudad no debe exceder los 100 caracteres")
    @Pattern(regexp = "^[A-Za-záéíóúÁÉÍÓÚñÑ ]+$", message = "La ciudad solo puede contener letras y espacios")
    private String nombreCiudad;

    // Validación para listas
    @NotNull(message = "Debe subir documentos para hacer validación de perfil")
    private List<String> archivoDocumento;

    @NotNull(message = "El tipo de documento no puede ser nulo")
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

    public List<String> getArchivoDocumento() {
        return archivoDocumento;
    }

    public void setArchivoDocumento(List<String> archivoDocumento) {
        this.archivoDocumento = archivoDocumento;
    }

    public List<String> getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(List<String> tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
}
