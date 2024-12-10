package com.vecindapp.repository.dto;


import com.vecindapp.entity.Rol;
import com.vecindapp.entity.Usuario;
import com.vecindapp.entity.UsuarioRol;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface IAdministradorMapper {

    @Mapping(target = "rolId", constant = "1")
    AdministradorDTO toDto(Usuario admin);

    @Mapping(target = "usuarioRols", expression = "java(mapRolIdToUsuarioRols(admindto.getRolId()))")
    Usuario toEntity(AdministradorDTO admindto);

    default List<UsuarioRol> mapRolIdToUsuarioRols(Integer rolId) {
        if (rolId == null) {
            return new ArrayList<>();
        }
        UsuarioRol usuarioRol = new UsuarioRol();
        Rol rol = new Rol();
        rol.setId(rolId);
        usuarioRol.setRole(rol);
        return List.of(usuarioRol);
    }

}
