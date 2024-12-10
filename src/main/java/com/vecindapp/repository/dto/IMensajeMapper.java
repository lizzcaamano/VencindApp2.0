package com.vecindapp.repository.dto;

import com.vecindapp.entity.Mensaje;
import com.vecindapp.entity.UsuarioRol;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IMensajeMapper {

    @Mapping(source = "chat.id" , target = "chatId")
    @Mapping(target = "senderRole", expression = "java(obtenerRol(mensaje))")
    @Mapping(target = "remitente", expression = "java(mensaje.getChat().getUsuario().getNombre())")
    @Mapping(target = "price", ignore = true)
    MensajeDTO toDTO(Mensaje mensaje);

    @Mapping(source = "chatId", target = "chat.id")
    Mensaje toEntity(MensajeDTO mensajeDTO);

    default String obtenerRol(Mensaje mensaje) {
        return mensaje.getChat().getUsuario().getUsuarioRols().stream()
                .map(usuarioRol -> usuarioRol.getRole().getNombre())
                .findFirst()
                .orElse("Desconocido");
    }
}
