package com.vecindapp.repository.dto;

import com.vecindapp.entity.Barrio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface IBarrioMapper {

    ILocalidadMapper mapper = Mappers.getMapper(ILocalidadMapper.class);
    //DTO a Entity
    @Mapping(target = "localidad", source = "localidad")
    BarrioDTO toDto(Barrio barrio);

    // Entity a DTO

    @Mapping(target =  "localidad", source = "localidad" )
    Barrio toEntity(BarrioDTO barrioDTO);
}
