package com.vecindapp.service;

import com.vecindapp.entity.Ubicacion;
import com.vecindapp.entity.Usuario;
import com.vecindapp.repository.dao.IUsuarioDAO;
import com.vecindapp.repository.dto.IUsuarioMapper;
import com.vecindapp.repository.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
public class UsuarioService implements IUsuarioService {

    @Autowired
    IUsuarioDAO usudao;

    @Autowired
    IUsuarioMapper usMap;


    @Override
    public UsuarioDTO findById(int id) {

        Usuario usuario = usudao.findById(id);

        if (usuario != null) {
            UsuarioDTO usuarioDTO = usMap.toDTO(usuario);

            //Verificamos si la ubicacion es nula
            if (usuario.getUbicacion() != null) {
                usuarioDTO.setUbicacionId(usuario.getUbicacion().getId());

            }else {
                usuarioDTO.setUbicacionId(0);
            }
            return usuarioDTO;
        }else {
            throw new RuntimeException("Usuario no encontrado");
        }
    }


    @Override
    public List<UsuarioDTO> ListUsuarios() {
        List<Usuario> usuarios = usudao.ListUsuarios();
        return usuarios.stream()
                .map( usuario -> {
                        //Pasar entidad a DTO
                        UsuarioDTO usuarioDTO = usMap.toDTO(usuario);

                        //Verificamos si la ubicacion es nula
                        if (usuario.getUbicacion() != null) {
                            usuarioDTO.setUbicacionId(usuario.getUbicacion().getId());
                        }else {
                            usuarioDTO.setUbicacionId(0);
                        }
                            return usuarioDTO;
                })
                .collect(Collectors.toList());

    }


    @Override
    public List<UsuarioDTO> insertUsuario(UsuarioDTO usuariodto) {

        //Buscamos si el email insgresado existe
        Optional<Usuario> emailExists = usudao.findByEmail(usuariodto.getEmail());

        //Verificamos, si existe no permite crear el usuario, si no existe permite crear usuario
        if (emailExists.isPresent()) {
            //Lanzamos una excepción
            throw new RuntimeException("El correo ya existe, intente iniciar sesión");
        }else {
            //Convertimos el DTO a entidad (para guardarlo en base de datos)
            Usuario usuario = usMap.toEntity(usuariodto);


            //Insertamos para ubicación (se insertan las ubicaciones que ya existen en la base)
            Ubicacion ubicacion = new Ubicacion();

            //if ( ubicacion.getId().equals(usuariodto.getUbicacionId())){
                ubicacion.setId(usuariodto.getUbicacionId());
            /*}else {

            }*/


            usudao.insertUsuario(usuario);
            return ListUsuarios();
        }

    }

    @Override
    public UsuarioDTO updateUsuario(Integer id, UsuarioDTO usuariodto) {
        Usuario usuarioExiste = usudao.findById(id);

        if(usuarioExiste == null) {
            throw new RuntimeException("El usuario no existe");


        }else{
            //Actualizamos los campos
            usuarioExiste.setNombre(usuariodto.getNombre());
            usuarioExiste.setEmail(usuariodto.getEmail());
            usuarioExiste.setFoto(usuariodto.getFoto());
            usuarioExiste.setPassword(usuariodto.getPassword());
            usuarioExiste.setTelefono(usuariodto.getTelefono());
            usuarioExiste.setDescripcion(usuariodto.getDescripcion());
            usuarioExiste.setFechaRegistro(usuariodto.getFechaRegistro());
            usuarioExiste.setEstado(usuariodto.getEstado());

            //Guardamos los cambios realizados
            Usuario usuarioOK = usudao.updateUsuario(usuarioExiste);

            //Devolvemos a DTO
            return usMap.toDTO(usuarioOK);
        }

    }


    @Override
    public List<Usuario> findByNombre(String nombre) {

        return List.of();
    }
}
