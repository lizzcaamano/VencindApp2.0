package com.vecindapp.repository.dto;


import com.vecindapp.entity.Reserva;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IReservaMapper {

    @Mapping(target = "usuarioId", source = "usuario.id")
    @Mapping(target = "servicioId", source = "servicio.id")
    @Mapping(target = "pagoId", source = "pago.id")
    ReservaDTO toDTO(Reserva reserva);

    @Mapping(target = "usuario.id", source = "usuarioId")
    @Mapping(target = "servicio.id", source = "servicioId")
    @Mapping(target = "pago.id", source = "pagoId")
    Reserva toEntity(ReservaDTO reservaDTO);
}

