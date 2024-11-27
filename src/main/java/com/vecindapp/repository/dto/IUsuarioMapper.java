package com.vecindapp.repository.dto;

import com.vecindapp.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper (componentModel = "spring")
public interface IUsuarioMapper {

    //Instancia para mapper
    IUsuarioMapper mapper = Mappers.getMapper(IUsuarioMapper.class);

    //Mapeos (se hacen el las relaciones, sobre los datos que queremos mostrar)
    //Mapeo para pasar de DTO a Entity
    @Mapping(target = "agendaId", source = "agenda.id")
    @Mapping(target = "calificacionId", source = "calificacion.id")
    @Mapping(target = "chatId", source = "chat.id")
    @Mapping(target = "documentoId", source = "documento.id")
    @Mapping(target = "favoritoId", source = "favorito.id")
    @Mapping(target = "reporteId", source = "reporte.id")
    @Mapping(target = "reservaId", source = "reserva.id")
    @Mapping(target = "rolId", source = "usuariorol.id")
    UsuarioDTO toDTO(Usuario usuario);

    //Mapeo para pasar de Entity a DTO
    @Mapping(target = "agenda.id", source = "agendaId")
    @Mapping(target = "calificacion.id", source = "calificacionId")
    @Mapping(target = "chat.id", source = "chatId")
    @Mapping(target = "documento.id", source = "documentoId")
    @Mapping(target = "favorito.id", source = "favoritoId")
    @Mapping(target = "reporte.id", source = "reporteId")
    @Mapping(target = "reserva.id", source = "reservaId")
    @Mapping(target = "usuariorol.id", source = "rolId")
    UsuarioDTO toEntity(UsuarioDTO usuarioDTO);
}
