package com.vecindapp.controller;

import com.vecindapp.repository.dto.AdministradorDTO;
import com.vecindapp.service.IAdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private IAdminService adminService;

    @GetMapping("/user-info")
    public String getUserInfo() {
        // Obtén la autenticación del contexto de seguridad
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            // Accede a los detalles del usuario autenticado
            String username = authentication.getName(); // Nombre de usuario
            return "Usuario autenticado: " + username;
        } else {
            return "No se ha encontrado autenticación";
        }
    }

    // Obtener todos los administradores
    @GetMapping("all")
    public ResponseEntity<List<AdministradorDTO>> getAdmins() {
        List<AdministradorDTO> admins = adminService.ListAdmin();
        if (admins.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // 204 No Content si no hay datos
        }
        return new ResponseEntity<>(admins, HttpStatus.OK);  // 200 OK con los datos
    }

    // Obtener un administrador por ID
    @GetMapping("single")
    public ResponseEntity<AdministradorDTO> getAdmin(@RequestParam("id") int id) {
        AdministradorDTO admin = adminService.findById(id);
        if (admin == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // 404 Not Found si no se encuentra
        }
        return new ResponseEntity<>(admin, HttpStatus.OK);  // 200 OK con el dato
    }

    // Obtener administradores por nombre
    @GetMapping("byname")
    public ResponseEntity<List<AdministradorDTO>> getAdminByName(@RequestParam("name") String name) {
        List<AdministradorDTO> admins = adminService.findByNombre(name);
        if (admins.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // 204 No Content si no hay coincidencias
        }
        return new ResponseEntity<>(admins, HttpStatus.OK);  // 200 OK con los datos
    }

    // Agregar un administrador
    @PostMapping("add")
    public ResponseEntity<List<AdministradorDTO>> addAdmin(@Valid @RequestBody AdministradorDTO administradorDTO) {
        adminService.insertAdmin(administradorDTO);
        List<AdministradorDTO> admins = adminService.ListAdmin();  // Obtener la lista de administradores después de agregar
        return new ResponseEntity<>(admins, HttpStatus.CREATED);  // 201 Created
    }

    // Actualizar un administrador por ID
    @PutMapping("upd")
    public ResponseEntity<AdministradorDTO> updateAdmin(@Valid @RequestParam("id") int id, @RequestBody AdministradorDTO administradorDTO) {
        AdministradorDTO updatedAdmin = adminService.updateAdmin(id, administradorDTO);
        if (updatedAdmin == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // 404 Not Found si no se encuentra el administrador
        }
        return new ResponseEntity<>(updatedAdmin, HttpStatus.OK);  // 200 OK con el administrador actualizado
    }
}
