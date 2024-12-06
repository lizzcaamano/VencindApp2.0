package com.vecindapp.repository.dto;

import com.vecindapp.entity.Barrio;
import com.vecindapp.entity.Localidad;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {ICiudadMapper.class})
public interface ILocalidadMapper {

    ILocalidadMapper mapper = Mappers.getMapper(ILocalidadMapper.class);
    //DTO a Entity
    @Mapping(target = "ciudad", source = "ciudad")
    LocalidadDTO toDto(Localidad localidad);

    // Entity a DTO
    @Mapping(target = "ciudad", source = "ciudad")
    Localidad toEntity(LocalidadDTO localidadDTO);
}
