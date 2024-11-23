package com.vecindapp.controller;


import com.vecindapp.entity.Usuario;
import com.vecindapp.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("user")
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    private IUsuarioService Uservice;

    @GetMapping("lista")
    public List<Usuario> lista(){
        return Uservice.ListUsuarios();
    }

}
