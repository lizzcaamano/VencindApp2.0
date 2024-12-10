package com.vecindapp.repository.dto;

import com.vecindapp.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ITrabajadorMapper {
    ITrabajadorMapper mapper = Mappers.getMapper(ITrabajadorMapper.class);

    // Entity a DTO
    @Mapping(target = "rolId", constant = "3")
    @Mapping(target = "ubicaciondir", source = "ubicacion.direccion")
    @Mapping(target = "nombreBarrio", source = "ubicacion.barrio.nombreBarrio")
    @Mapping(target = "nombreLocalidad", source = "ubicacion.barrio.localidad.nombreLocalidad")
    @Mapping(target = "nombreCiudad", source = "ubicacion.barrio.localidad.ciudad.nombreCiudad")
    @Mapping(target = "archivoDocumento", expression = "java(mapArchivoDocumento(trabajador))") // Manejo de lista de documentos
    @Mapping(target = "tipoDocumento", expression = "java(mapTipoDocumento(trabajador))")
    TrabajadorDTO toDto(Usuario trabajador);

    // DTO a Entity
    @Mapping(target = "usuarioRols", expression = "java(mapRolIdToUsuarioRols(trabajadorDTO.getRolId()))")
    @Mapping(target = "ubicacion.direccion", source = "ubicaciondir")
    @Mapping(target = "ubicacion.barrio.nombreBarrio", source = "nombreBarrio")
    @Mapping(target = "ubicacion.barrio.localidad.nombreLocalidad", source = "nombreLocalidad")
    @Mapping(target = "ubicacion.barrio.localidad.ciudad.nombreCiudad", source = "nombreCiudad")
    @Mapping(target = "documentos", expression = "java(mapDocumentos(trabajadorDTO))") // Manejo seguro para documentos
    Usuario toEntity(TrabajadorDTO trabajadorDTO);

    default List<UsuarioRol> mapRolIdToUsuarioRols(Integer rolId) {
        if (rolId == null) {
            return new ArrayList<>();
        }
        UsuarioRol usuarioRol = new UsuarioRol();
        Rol rol = new Rol();
        rol.setId(rolId);
        usuarioRol.setRole(rol);
        return List.of(usuarioRol);
    }


    // Métodos auxiliares para listas
    default List<String> mapArchivoDocumento(Usuario usuario) {
        if (usuario.getDocumentos() == null) return new ArrayList<>();
        return usuario.getDocumentos().stream()
                .map(Documento::getArchivoUrl)
                .collect(Collectors.toList());
    }

    default List<String> mapTipoDocumento(Usuario usuario) {
        if (usuario.getDocumentos() == null) return new ArrayList<>();
        return usuario.getDocumentos().stream()
                .map(doc -> doc.getTipoDocumento().getNombre())
                .collect(Collectors.toList());
    }

    default List<Documento> mapDocumentos(TrabajadorDTO trabajadorDTO) {
        if (trabajadorDTO.getArchivoDocumento() == null || trabajadorDTO.getTipoDocumento() == null) {
            return new ArrayList<>();
        }

        List<Documento> documentos = new ArrayList<>();
        for (int i = 0; i < trabajadorDTO.getArchivoDocumento().size(); i++) {
            Documento documento = new Documento();
            Tipodocumento tipoDocumento = new Tipodocumento();

            tipoDocumento.setNombre(trabajadorDTO.getTipoDocumento().get(i));
            documento.setArchivoUrl(trabajadorDTO.getArchivoDocumento().get(i));
            documento.setTipoDocumento(tipoDocumento);

            documentos.add(documento);
        }
        return documentos;
    }



}
