package com.vecindapp.controller;


import com.vecindapp.repository.dto.ClienteDTO;
import com.vecindapp.repository.dto.TrabajadorDTO;
import com.vecindapp.service.IClienteService;
import com.vecindapp.service.ITrabajadorService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
