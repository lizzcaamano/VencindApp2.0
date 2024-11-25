package com.vecindapp.controller;


import com.vecindapp.entity.Usuario;
import com.vecindapp.service.IUsuarioService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("user")
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    private IUsuarioService Uservice;

    @GetMapping("list")
    public List<Usuario> lista(){
        return Uservice.ListUsuarios();
    }

    @GetMapping("byid")
    public Usuario findById(@RequestParam("id") int id){
        return Uservice.findById(id);
    }

    @GetMapping(value="byname/{name}", consumes=MediaType.APPLICATION_JSON_VALUE)
    public List<Usuario> findByName(@PathParam("name") String name){
        return Uservice.findByNombre(name);
    }

    @PostMapping(value="/insert", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Usuario> insert(@RequestBody Usuario usuario){
        Uservice.insertUsuario(usuario);
        return Uservice.ListUsuarios();
    }

}
