package com.vecindapp.controller;


import com.vecindapp.entity.Usuario;
import com.vecindapp.repository.dto.TrabajadorDTO;
import com.vecindapp.service.ITrabajadorService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("trabajador")

public class TrabajadorController {

    @Autowired
    private ITrabajadorService traService;

    @GetMapping("all")
    public List<TrabajadorDTO> Trabajadores() {
        return traService.ListTrabajadores();
    }

    @GetMapping("single")
    public TrabajadorDTO TrabajadorById(@RequestParam("id") int id) {
        return traService.findById(id);
    }

    @GetMapping("byname")
    public List<TrabajadorDTO> TrabajadorByName(@RequestParam("name") String name) {
        return traService.findByNombre(name);
    }


    @PostMapping("add")
    public List<TrabajadorDTO> Add(@RequestBody TrabajadorDTO trabajadorDTO) {
        traService.insertTrabajador(trabajadorDTO);
        return Trabajadores();
    }

    @PutMapping("upd")
    public TrabajadorDTO Upd(@RequestParam("id") Integer id, @RequestBody TrabajadorDTO trabajadordto) {

        return traService.updateTrabajador(id, trabajadordto);
    }

    @PatchMapping("upde")
    public TrabajadorDTO EstadoTrabajador(@RequestParam("id") int id, @RequestParam("estado") String estado){

        return traService.updateByEstado(id, estado);
    }




}
