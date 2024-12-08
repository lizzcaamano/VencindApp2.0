package com.vecindapp.repository.dto;

import com.vecindapp.entity.Rol;
import com.vecindapp.entity.Usuario;
import com.vecindapp.entity.UsuarioRol;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ITrabajadorMapper {
    ITrabajadorMapper mapper = Mappers.getMapper(ITrabajadorMapper.class);

    // Entity a DTO
    @Mapping(target = "rolId", constant = "3")
    @Mapping(target = "ubicaciondir", source = "ubicacion.direccion")
    @Mapping(target = "nombreBarrio", source = "ubicacion.barrio.nombreBarrio")
    @Mapping(target = "nombreLocalidad", source = "ubicacion.barrio.localidad.nombreLocalidad")
    @Mapping(target = "nombreCiudad", source = "ubicacion.barrio.localidad.ciudad.nombreCiudad")
    //@Mapping(target = "archivoDocumento", source = "documento.tipodocumento.nombretip")
    //@Mapping(target = "archivoDocumento", source = "documentos[0].archivoUrl")  // URL del archivo
    //@Mapping(target = "tipoDocumento", source = "documentos[0].tipoDocumento.nombre")  // Nombre del tipo de documento
    TrabajadorDTO toDto(Usuario trabajador);

    // DTO a Entity
    @Mapping(target = "usuarioRols", expression = "java(mapRolIdToUsuarioRols(trabajadorDTO.getRolId()))")
    @Mapping(target = "ubicacion.direccion", source = "ubicaciondir")
    @Mapping(target = "ubicacion.barrio.nombreBarrio", source = "nombreBarrio")
    @Mapping(target = "ubicacion.barrio.localidad.nombreLocalidad", source = "nombreLocalidad")
    @Mapping(target = "ubicacion.barrio.localidad.ciudad.nombreCiudad", source = "nombreCiudad")
    //@Mapping(target = "documentos[0].archivoUrl", source = "archivoDocumento")  // Mapeo de la URL al campo del documento
    //@Mapping(target = "documentos[0].tipoDocumento.nombre", source = "tipoDocumento")  // Mapeo del nombre del tipo de documento
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

}
