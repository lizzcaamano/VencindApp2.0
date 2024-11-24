package com.vecindapp.controller;


import com.vecindapp.entity.Agenda;
import com.vecindapp.repository.dto.AgendaDTO;
import com.vecindapp.service.IAgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("agenda")
public class AgendaController {

    @Autowired
    IAgendaService agendaService;

    @GetMapping(value= "lista")
    public List<Agenda> getAllAgendas(){
        return agendaService.listAllAgendas();
    }

    /*@PostMapping(value="insert", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insertAgenda(@RequestBody Agenda agenda){
        agendaService.addAgenda(agenda);
    }*/

    @PostMapping(value="insert",  produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AgendaDTO>> addAgenda(@RequestBody AgendaDTO agendaDTO){
        List<AgendaDTO> agendas = agendaService.addAgenda(agendaDTO);

        return ResponseEntity.ok(agendas);
    }

}
