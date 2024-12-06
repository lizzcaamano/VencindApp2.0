package com.vecindapp.repository.dto;

import com.vecindapp.entity.Mensaje;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IMensajeMapper {

    @Mapping(source = "chat.id" , target = "chatId")
    MensajeDTO toDTO(Mensaje mensaje);

    @Mapping(source = "chatId", target = "chat.id")
    Mensaje toEntity(MensajeDTO mensajeDTO);
}
