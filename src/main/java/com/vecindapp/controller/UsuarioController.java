package com.vecindapp.controller;


import com.vecindapp.entity.Usuario;
import com.vecindapp.repository.dto.UsuarioDTO;
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

    /*@Autowired
    private IUsuarioService Uservice;

    @GetMapping("list")
    public List<UsuarioDTO> lista(){
        return Uservice.ListUsuarios();
    }

    @GetMapping("byid")
    public UsuarioDTO findById(@RequestParam("id") int id){
        return Uservice.findById(id);
    }



    @GetMapping(value="/byname", consumes=MediaType.APPLICATION_JSON_VALUE)
    public List<UsuarioDTO> findByName(@RequestParam("name") String name){
        return Uservice.findByNombre(name);

    }

    @PostMapping(value="/insert", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<UsuarioDTO> insert(@RequestBody UsuarioDTO usuario){
        Uservice.insertUsuario(usuario);
        return Uservice.ListUsuarios();
    }

    @PutMapping(value="/upd", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UsuarioDTO update(@RequestBody UsuarioDTO usuario, @PathParam("id") Integer id){

        return Uservice.updateUsuario(id, usuario);
    }*/

}
