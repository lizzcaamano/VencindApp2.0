package com.vecindapp.controller;

import com.vecindapp.repository.dto.ServicioDTO;
import com.vecindapp.service.IServicioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("servicio")
public class ServicioController {

    @Autowired
    IServicioService servicioService;

    @GetMapping("list")
    public List<ServicioDTO> getServicios(){
        return servicioService.listAllServicios();
    }

    @PostMapping(value="insert", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ServicioDTO>> addServicio(@Valid @RequestBody ServicioDTO servicioDTO){
        List<ServicioDTO> servicios = servicioService.addServicio(servicioDTO);

        return ResponseEntity.ok(servicios);
    }

    @PutMapping(value = "upd")
    public ResponseEntity<ServicioDTO> updateServicio(){
        return null;
    }
}
