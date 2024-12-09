package com.vecindapp.controller;


import com.vecindapp.repository.dto.ClienteDTO;
import com.vecindapp.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("cliente")

public class ClienteController {

    @Autowired
    private IClienteService cliService;

    @GetMapping("all")
    public List<ClienteDTO> Clientes() {
        return cliService.ListClientes();
    }

    @GetMapping("single")
    public ClienteDTO Cliente(@RequestParam("id") int id) {
        return cliService.findById(id);
    }

    @GetMapping("byname")
    public List<ClienteDTO> Cliente(@RequestParam("name") String name) {
        return cliService.findByNombre(name);
    }

    @PostMapping("add")
    public List<ClienteDTO> Add(@RequestBody ClienteDTO clienteDTO) {
        cliService.insertCliente(clienteDTO);
        return Clientes();
    }

    @PutMapping("upd")
    public ClienteDTO Upd(@RequestParam("id") int id, @RequestBody ClienteDTO clienteDTO) {

        return cliService.updateCliente(id, clienteDTO);
    }




}
