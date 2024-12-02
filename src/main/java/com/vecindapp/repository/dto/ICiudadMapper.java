package com.vecindapp.repository.dto;

import com.vecindapp.entity.Barrio;
import com.vecindapp.entity.Ciudad;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ICiudadMapper {
    ILocalidadMapper mapper = Mappers.getMapper(ILocalidadMapper.class);

    CiudadDTO toDto(Ciudad ciudad);
    Ciudad toEntity(CiudadDTO ciudadDTO);
}
