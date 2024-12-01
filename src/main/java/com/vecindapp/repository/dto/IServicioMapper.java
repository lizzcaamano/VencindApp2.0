package com.vecindapp.repository.dto;

import com.vecindapp.entity.Servicio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface IServicioMapper {

    IServicioMapper mapper = Mappers.getMapper(IServicioMapper.class);

    @Mapping(target = "categoriaName", source = "categoria.nombre")
    ServicioDTO toDTO(Servicio servicio);

    @Mapping(target = "categoria.nombre", source = "categoriaName")
    Servicio toEntity(ServicioDTO servicioDTO);
}
