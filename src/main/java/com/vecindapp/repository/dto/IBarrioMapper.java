package com.vecindapp.repository.dto;

import com.vecindapp.entity.Barrio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.io.Console;

@Mapper(componentModel = "spring")
public interface IBarrioMapper {

    IBarrioMapper mapper = Mappers.getMapper(IBarrioMapper.class);
    //DTO a Entity
    @Mapping(target = "localidad", source = "localidad.nombreLocalidad")
    @Mapping(target = "nombreCiudad", source = "localidad.ciudad.nombreCiudad")
    BarrioDTO toDto(Barrio barrio);


    // Entity a DTO
    @Mapping(target =  "localidad.nombreLocalidad", source = "localidad" )
    @Mapping(target = "localidad.ciudad.nombreCiudad", source = "nombreCiudad")
    Barrio toEntity(BarrioDTO barrioDTO);


}
