package com.vecindapp.repository.dto;

import com.vecindapp.entity.Rol;
import com.vecindapp.entity.Usuario;
import com.vecindapp.entity.UsuarioRol;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface IClienteMapper {
    IClienteMapper mapper = Mappers.getMapper(IClienteMapper.class);

    // Entity a DTO
    @Mapping(target = "rolId", constant = "3")
    @Mapping(target = "ubicacion", source = "ubicacion")
    ClienteDTO toDto(Usuario cliente);

    // DTO a Entity
    @Mapping(target = "usuarioRols", expression = "java(mapRolIdToUsuarioRols(clienteDTO.getRolId()))")
    @Mapping(target = "ubicacion", source = "ubicacion")
    Usuario toEntity(ClienteDTO clienteDTO);

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
