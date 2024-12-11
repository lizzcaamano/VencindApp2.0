package com.vecindapp.service;

import com.vecindapp.entity.*;
import com.vecindapp.repository.dao.*;
import com.vecindapp.repository.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
public class UsuarioService implements IUsuarioService {

    @Autowired
    IUsuarioDAO usudao;

    @Autowired
    IUsuarioMapper usMap;
    @Autowired
    IUbicacionMapper ubiMap;


    @Autowired
    IBarrioDAO barrioDao;
    @Autowired
    IUbicacionDAO ubicacionDao;
    @Autowired
    ICiudadDAO ciudadDao;
    @Autowired
    ILocalidadDAO localidadDao;



    @Override
    public UsuarioDTO findById(int id) {

        Usuario usuario = usudao.findById(id);

        if (usuario != null) {
            UsuarioDTO usuarioDTO = usMap.toDTO(usuario);
            if (usuario.getUbicacion() != null) {

                Ubicacion ubicacion = usuario.getUbicacion();
                Barrio barrio = ubicacion.getBarrio();
                Localidad localidad = barrio.getLocalidad();
                Ciudad ciudad = localidad.getCiudad();

                if (barrio != null && ciudad != null && ubicacion != null) {
                    UbicacionDTO ubicacionDTO = ubiMap.toDTO(ubicacion);
                    ubicacionDTO.setId(ubicacion.getId());

                    /*BarrioDTO barrioDTO = ubicacionDTO.getBarrio();
                    barrioDTO.getNombreLocalidad();
                    localidadDTO.setNombre(localidad.getNombreLocalidad());
                    localidadDTO.getCiudad().setNombre(ciudad.getNombreCiudad());*/
                    usuarioDTO.setUbicacion(ubicacionDTO);
                }else {
                    usuarioDTO.setUbicacion(null);
                }

            } else {
                usuarioDTO.setUbicacion(null);
            }



            return usuarioDTO;
        }else {
            throw new RuntimeException("Usuario no encontrado");
        }
    }


    @Override
    public List<UsuarioDTO> ListUsuarios() {
        List<Usuario> usuarios = usudao.ListUsuarios(); // Obtener los usuarios desde el DAO

        return usuarios.stream()
                .map(usuario -> {
                    // Mapear usuario a UsuarioDTO
                    UsuarioDTO usuarioDTO = usMap.toDTO(usuario);



                    if (usuario.getUbicacion() != null) {

                        Ubicacion ubicacion = usuario.getUbicacion();
                        Barrio barrio = ubicacion.getBarrio();
                        Localidad localidad = barrio.getLocalidad();
                        Ciudad ciudad = localidad.getCiudad();

                        if (barrio != null && ciudad != null && ubicacion != null) {
                            UbicacionDTO ubicacionDTO = ubiMap.toDTO(ubicacion);
                            ubicacionDTO.setId(ubicacion.getId());

                           /* BarrioDTO barrioDTO = ubicacionDTO.getBarrio();
                            LocalidadDTO localidadDTO = barrioDTO.getLocalidad();
                            localidadDTO.setNombre(localidad.getNombreLocalidad());
                            localidadDTO.getCiudad().setNombre(ciudad.getNombreCiudad());*/
                            usuarioDTO.setUbicacion(ubicacionDTO);
                        }else {
                            usuarioDTO.setUbicacion(null);
                        }

                    } else {
                        usuarioDTO.setUbicacion(null);
                    }

                    return usuarioDTO;
                })
                .collect(Collectors.toList());
    }


    @Override
    public List<UsuarioDTO> insertUsuario(UsuarioDTO usuariodto) {
        // Verificar si el email ya existe
        Usuario emailExists = usudao.findByEmail(usuariodto.getEmail());

        // Convertir el DTO a entidad (Usuario)
        Usuario usuario = usMap.toEntity(usuariodto);

        // Crear la Ubicación a partir del DTO
        Ubicacion ubicacion = ubiMap.toEntity(usuariodto.getUbicacion());

        // Verificar si la Ubicación tiene un ID válido
        if (ubicacion.getId() != null) {
            // Si el ID de la Ubicación no es null, verificar si ya existe en la base de datos
            Optional<Ubicacion> ubicacionExists = Optional.ofNullable(ubicacionDao.findById(ubicacion.getId()));
            if (ubicacionExists.isPresent()) {
                // Si existe, recuperar la Ubicación existente
                ubicacion = ubicacionExists.get();
            } else {
                // Si no existe, insertar la Ubicación en la base de datos
                ubicacion = ubicacionDao.insertUbicacion(ubicacion); // Guarda la Ubicación y asigna el id
            }
        } else {
            // Si el ID de la Ubicación es null, insertar la Ubicación sin buscarla en la base de datos
            ubicacion = ubicacionDao.insertUbicacion(ubicacion); // Asume que la ubicación no existe aún
        }

        // Verificar y guardar el Barrio si no existe
        Barrio barrio = ubicacion.getBarrio();
        if (barrio != null) {
            Optional<Barrio> barrioExists = Optional.ofNullable(barrioDao.findByNombreBarrio(barrio.getNombreBarrio()));
            if (!barrioExists.isPresent()) {
                barrioDao.insertBarrio(barrio); // Guardar el Barrio si no existe
            } else {
                barrio = barrioExists.get(); // Recuperar el Barrio existente
            }
            ubicacion.setBarrio(barrio); // Asociar el Barrio a la Ubicación
        }

        // Asociar la Ubicación al Usuario
        usuario.setUbicacion(ubicacion);

        // Guardar el Usuario
        usudao.insertUsuario(usuario);

        // Retornar la lista de usuarios
        return ListUsuarios();
    }




    /*public List<UsuarioDTO> insertUsuario(UsuarioDTO usuariodto) {

        //Buscamos si el email insgresado existe
        Optional<Usuario> emailExists = usudao.findByEmail(usuariodto.getEmail());

        //Verificamos, si existe no permite crear el usuario, si no existe permite crear usuario
        if (emailExists.isPresent()) {
            //Lanzamos una excepción
            throw new RuntimeException("El correo ya existe, intente iniciar sesión");
        }else {
            //Convertimos el DTO a entidad (para guardarlo en base de datos)
            Usuario usuario = usMap.toEntity(usuariodto);

            //Insertamos para ubicación
           Ubicacion ubicacion = ubiMap.toEntity(usuariodto.getUbicacion());
           ubicacionDao.insertUbicacion(ubicacion);

           //insertamos para barrio
            //buscamos
            Optional<Barrio> barrioExists = barrioDao.findByNombreBarrio(BarrioDTO.);



            //if ( ubicacion.getId().equals(usuariodto.getUbicacionId())){
               //ubicacion.setId(usuariodto.getUbicacionId());
            /*}else {

            }*/


           // usudao.insertUsuario(usuario);
           // return ListUsuarios();
        //}

    //}

    /*
    @Override
    public UsuarioDTO updateUsuario(Integer id, UsuarioDTO usuariodto) {
        Usuario usuarioExiste = usudao.findById(id);

        if(usuarioExiste == null) {
            throw new RuntimeException("El usuario no existe");


        }else{
            //Actualizamos los campos
            usuarioExiste.setNombre(usuariodto.getNombre());
            usuarioExiste.setEmail(usuariodto.getEmail());
            usuarioExiste.setFoto(usuariodto.getFoto());
            usuarioExiste.setPassword(usuariodto.getPassword());
            usuarioExiste.setTelefono(usuariodto.getTelefono());
            usuarioExiste.setDescripcion(usuariodto.getDescripcion());
            usuarioExiste.setFechaRegistro(usuariodto.getFechaRegistro());
            usuarioExiste.setEstado(usuariodto.getEstado());

            //Guardamos los cambios realizados
            Usuario usuarioOK = usudao.updateUsuario(usuarioExiste);

            //Devolvemos a DTO
            return usMap.toDTO(usuarioOK);
        }

    }*/



    @Override
    public UsuarioDTO updateUsuario(Integer id, UsuarioDTO usuariodto) {
        // Buscar el usuario existente por ID
        Usuario usuarioExiste = usudao.findById(id);

        if (usuarioExiste == null) {
            throw new RuntimeException("El usuario no existe");
        } else {
            // Actualizar los campos del usuario
            usuarioExiste.setNombre(usuariodto.getNombre());
            usuarioExiste.setEmail(usuariodto.getEmail());
            usuarioExiste.setFoto(usuariodto.getFoto());
            usuarioExiste.setPassword(usuariodto.getPassword());
            usuarioExiste.setTelefono(usuariodto.getTelefono());
            usuarioExiste.setDescripcion(usuariodto.getDescripcion());
            usuarioExiste.setFechaRegistro(usuariodto.getFechaRegistro());
            usuarioExiste.setEstado(usuariodto.getEstado());

            // Verificar y actualizar la Ubicación si se ha enviado en el DTO
            if (usuariodto.getUbicacion() != null) {
                Ubicacion ubicacion = ubiMap.toEntity(usuariodto.getUbicacion());

                // Si la ubicación tiene un ID, verificamos si existe y actualizamos
                if (ubicacion.getId() != null) {
                    Optional<Ubicacion> ubicacionExistente = Optional.ofNullable(ubicacionDao.findById(ubicacion.getId()));
                    if (ubicacionExistente.isPresent()) {
                        ubicacion = ubicacionExistente.get();
                    } else {
                        ubicacion = ubicacionDao.insertUbicacion(ubicacion); // Si no existe, se inserta
                    }
                } else {
                    ubicacion = ubicacionDao.insertUbicacion(ubicacion); // Si no tiene ID, lo insertamos
                }

                // Asociar la ubicación actualizada al usuario
                usuarioExiste.setUbicacion(ubicacion);

                // Verificar y asociar el Barrio
                Barrio barrio = ubicacion.getBarrio();
                if (barrio != null) {
                    Optional<Barrio> barrioExists = Optional.ofNullable(barrioDao.findByNombreBarrio(barrio.getNombreBarrio()));
                    if (!barrioExists.isPresent()) {
                        barrioDao.insertBarrio(barrio); // Guardar el Barrio si no existe
                    } else {
                        barrio = barrioExists.get(); // Recuperar el Barrio existente
                    }
                    ubicacion.setBarrio(barrio); // Asociar el Barrio a la Ubicación
                }
            }

            // Guardar el usuario actualizado en la base de datos
            Usuario usuarioActualizado = usudao.updateUsuario(usuarioExiste);

            // Retornar el DTO del usuario actualizado
            return usMap.toDTO(usuarioActualizado);
        }
    }



    @Override
    public List<UsuarioDTO> findByNombre(String nombre) {
        // Obtener todos los usuarios desde el DAO
        List<Usuario> usuarios = usudao.ListUsuarios();

        // Filtrar los usuarios por nombre
        List<Usuario> usuariosFiltrados = usuarios.stream()
                .filter(usuario -> usuario.getNombre() != null &&
                        usuario.getNombre().toLowerCase().startsWith(nombre.toLowerCase()))
                .collect(Collectors.toList());


        if (usuariosFiltrados.isEmpty()) {
            throw new RuntimeException("No se encontraron usuarios con el nombre especificado");
        }

        // Convertir los usuarios filtrados a UsuarioDTO
        return usuariosFiltrados.stream().map(usuario -> {
            UsuarioDTO usuarioDTO = usMap.toDTO(usuario);

            if (usuario.getUbicacion() != null) {
                Ubicacion ubicacion = usuario.getUbicacion();
                Barrio barrio = ubicacion.getBarrio();
                Localidad localidad = barrio != null ? barrio.getLocalidad() : null;
                Ciudad ciudad = localidad != null ? localidad.getCiudad() : null;

                if (ubicacion != null && barrio != null && localidad != null && ciudad != null) {
                    UbicacionDTO ubicacionDTO = ubiMap.toDTO(ubicacion);
                    ubicacionDTO.setId(ubicacion.getId());

                   /* BarrioDTO barrioDTO = ubicacionDTO.getBarrio();
                    LocalidadDTO localidadDTO = barrioDTO.getLocalidad();
                    localidadDTO.setNombre(localidad.getNombreLocalidad());
                    localidadDTO.getCiudad().setNombre(ciudad.getNombreCiudad());*/

                    usuarioDTO.setUbicacion(ubicacionDTO);
                } else {
                    usuarioDTO.setUbicacion(null);
                }
            } else {
                usuarioDTO.setUbicacion(null);
            }

            return usuarioDTO;
        }).collect(Collectors.toList());
    }

}
