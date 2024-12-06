package com.vecindapp.repository.dto;

import com.vecindapp.entity.Chat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface IChatMapper {

    //Instancia de Mapper
    IAgendaMapper mapper = Mappers.getMapper(IAgendaMapper.class);

    @Mapping(source = "usuario.id", target = "clienteId")
    @Mapping(source = "usuario.nombre", target = "clienteNombre")
    @Mapping(source = "trabajador.id", target = "trabajadorId")
    @Mapping(source = "trabajador.nombre", target = "trabajadorNombre")
    @Mapping(source = "id", target = "chatId")
    @Mapping(source = "roomId", target = "roomId")
    @Mapping(source = "servicioId", target = "servicioId")
    @Mapping(source = "fechaInicio", target = "fechaInicio")
    ChatDTO toDTO(Chat chat);

    // Mapeo de ChatDTO a Chat
    @Mapping(source = "clienteId", target = "usuario.id")
    @Mapping(source = "trabajadorId", target = "trabajador.id")
    @Mapping(source = "chatId", target = "id")
    @Mapping(source = "roomId", target = "roomId")
    @Mapping(source = "servicioId", target = "servicioId")
    @Mapping(source = "fechaInicio", target = "fechaInicio")
    Chat toEntity(ChatDTO chatDTO);

}
