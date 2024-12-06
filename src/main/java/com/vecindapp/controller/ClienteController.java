package com.vecindapp.controller;


import com.vecindapp.repository.dto.ClienteDTO;
import com.vecindapp.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("cliente")
@CrossOrigin("*")
public class ClienteController {

    @Autowired
    private IClienteService cliService;

    @GetMapping("all")
    public List<ClienteDTO> Clientes() {
        return cliService.ListClientes();
    }

    @PostMapping("add")
    public List<ClienteDTO> Add(@RequestBody ClienteDTO clienteDTO) {
        cliService.insertCliente(clienteDTO);
        return Clientes();
    }




}
