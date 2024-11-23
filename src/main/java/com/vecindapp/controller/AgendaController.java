package com.vecindapp.controller;


import com.vecindapp.entity.Agenda;
import com.vecindapp.service.IAgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("agenda")
public class AgendaController {

    @Autowired
    IAgendaService agendaService;

    @GetMapping(value= "listaA")
    public List<Agenda> getAllAgendas(){
        return agendaService.listAllAgendas();
    }

    @GetMapping(value="insert")
    public List<Agenda> insertAgenda(){
        return null;
    }

}
