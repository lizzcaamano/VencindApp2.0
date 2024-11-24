package com.vecindapp.repository.dto;
import com.vecindapp.entity.Agenda;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface IAgendaMapper {

    //Instancia de Mapper
    IAgendaMapper mapper = Mappers.getMapper(IAgendaMapper.class);

    //Mapeos
    @Mapping(target = "notificacionId", source = "notificacion.id")
    @Mapping(target = "usuarioId", source = "usuario.id")
    AgendaDTO toDTO(Agenda agenda);

    @Mapping(target = "notificacion.id", source = "notificacionId")
    @Mapping(target = "usuario.id", source = "usuarioId")
    Agenda toEntity(AgendaDTO agendaDTO);
}
