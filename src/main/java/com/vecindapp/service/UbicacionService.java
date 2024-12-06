package com.vecindapp.service;

import com.vecindapp.entity.Barrio;
import com.vecindapp.entity.Ciudad;
import com.vecindapp.entity.Localidad;
import com.vecindapp.entity.Ubicacion;
import com.vecindapp.repository.dao.IBarrioDAO;
import com.vecindapp.repository.dao.ICiudadDAO;
import com.vecindapp.repository.dao.ILocalidadDAO;
import com.vecindapp.repository.dao.IUbicacionDAO;
import com.vecindapp.repository.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UbicacionService implements IUbicacionService {

    private static final Logger log = LoggerFactory.getLogger(UbicacionService.class);
    @Autowired
    IUbicacionDAO ubidao;

    //Para insertar directamente en las entidades
    @Autowired
    IBarrioDAO barriodao;
    @Autowired
    ILocalidadDAO localidaddao;
    @Autowired
    ICiudadDAO ciudaddao;

    @Autowired
    IUbicacionMapper ubimap;
    @Autowired
    IBarrioMapper barmap;

    @Override
    public Ubicacion findById(int id) {

        return null;
    }

    @Override
    public List<Ubicacion> listUbicaciones() {
        List<Ubicacion> ubicaciones = ubidao.listUbicaciones();

        return null;
    }

    @Override
    public Ubicacion insertUbicacion(Ubicacion ubicacion) {

        Barrio barrio = ubicacion.getBarrio();
        Localidad localidad = barrio.getLocalidad();
        Ciudad ciudad = localidad.getCiudad();
        //Insertar barrio
        // Verificar y guardar el Barrio si no existe
        if (barrio != null) {
            Optional<Barrio> barrioExists = Optional.ofNullable(barriodao.findByNombreBarrio(barrio.getNombreBarrio()));
            ciudaddao.findByNombreCiudad(ciudad.getNombreCiudad());
            Optional<Localidad> localidadExists = Optional.ofNullable(localidaddao.findByNombreLocalidad(localidad.getNombreLocalidad()));
            if (!barrioExists.isPresent()) {
                localidad = localidadExists.get();
                barrio.setLocalidad(localidad);
                barriodao.insertBarrio(barrio);// Guardar el Barrio si no existe
            } else {
                barrio = barrioExists.get(); // Recuperar el Barrio existente
            }
            ubicacion.setBarrio(barrio); // Asociar el Barrio a la Ubicación
        }

        //Guardar la ubicacion
        ubidao.insertUbicacion(ubicacion);


        return ubicacion;
    }

    @Override
    public Ubicacion updateUbicacion(Ubicacion ubicacion) {

       Barrio barrio = ubicacion.getBarrio();

       //Actualizar datos
        ubicacion.setDireccion(ubicacion.getDireccion());


        Localidad localidad = barrio.getLocalidad();
        Ciudad ciudad = localidad.getCiudad();
        //Insertar barrio
        // Verificar y guardar el Barrio si no existe
        if (barrio != null) {
            Optional<Barrio> barrioExists = Optional.ofNullable(barriodao.findByNombreBarrio(barrio.getNombreBarrio()));
            ciudaddao.findByNombreCiudad(ciudad.getNombreCiudad());
            Optional<Localidad> localidadExists = Optional.ofNullable(localidaddao.findByNombreLocalidad(localidad.getNombreLocalidad()));
            if (!barrioExists.isPresent()) {
                localidad = localidadExists.get();
                barrio.setLocalidad(localidad);
                barriodao.insertBarrio(barrio);// Guardar el Barrio si no existe
            } else {
                barrio = barrioExists.get();// Recuperar el Barrio existente
            }
            ubicacion.setBarrio(barrio); // Asociar el Barrio a la Ubicación
        }

        return ubidao.updateUbicacion(ubicacion);
    }
}
