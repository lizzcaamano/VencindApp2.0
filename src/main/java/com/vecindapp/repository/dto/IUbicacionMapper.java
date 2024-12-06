package com.vecindapp.repository.dto;


import com.vecindapp.entity.Barrio;
import com.vecindapp.entity.Ubicacion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {IBarrioMapper.class})
//@Mapper(componentModel = "spring")
public interface IUbicacionMapper {

    IUbicacionMapper INSTANCE = Mappers.getMapper(IUbicacionMapper.class);

    @Mapping(target = "barrio", source = "barrio")

    UbicacionDTO toDTO(Ubicacion ubicacion);

    //Mapeo para pasar de Entity a DTO
    @Mapping(target = "barrio", source = "barrio")
    //@Mapping(target = "barrio.localidad", source = "localidad")
    //@Mapping(target = "barrio.localidad.ciudad", source = "ciudad")

    Ubicacion toEntity(UbicacionDTO ubicacionDTO);


}
