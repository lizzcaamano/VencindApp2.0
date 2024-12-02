package com.vecindapp.repository.dto;

import com.vecindapp.entity.Usuario;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;



@Mapper (componentModel = "spring")
public interface IUsuarioMapper {

    //Instancia para mapper
    IUsuarioMapper mapper = Mappers.getMapper(IUsuarioMapper.class);

    //Mapeos (se hacen el las relaciones, sobre los datos que queremos mostrar)
    //Mapeo para pasar de DTO a Entity
    // Mapeo de DTO a Entity
    @Mapping(target = "ubicacion", source = "ubicacion")
    UsuarioDTO toDTO(Usuario usuario);

    //Mapeo para pasar de Entity a DTO
    @Mapping(target = "ubicacion", source = "ubicacion")
    Usuario toEntity(UsuarioDTO usuarioDTO);

}