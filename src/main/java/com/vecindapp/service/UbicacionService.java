package com.vecindapp.service;

import com.vecindapp.entity.Barrio;
import com.vecindapp.entity.Ubicacion;
import com.vecindapp.repository.dao.IBarrioDAO;
import com.vecindapp.repository.dao.ICiudadDAO;
import com.vecindapp.repository.dao.ILocalidadDAO;
import com.vecindapp.repository.dao.IUbicacionDAO;
import com.vecindapp.repository.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UbicacionService implements IUbicacionService {

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
    public UbicacionDTO findById(int id) {

        return null;
    }

    @Override
    public List<UbicacionDTO> listUbicaciones() {
        List<Ubicacion> ubicaciones = ubidao.listUbicaciones();

        return ubicaciones.stream()
                .map(ubimap::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UbicacionDTO insertUbicacion(UbicacionDTO ubicacionDTO) {
        Ubicacion ubicacion = ubimap.toEntity(ubicacionDTO);
        Barrio barrio = barmap.toEntity(ubicacionDTO.getBarrio());
        //Insertar barrio
        // Verificar y guardar el Barrio si no existe
        BarrioDTO barriodto = ubicacionDTO.getBarrio();



        if (barriodto != null) {
            Optional<Barrio> barrioExists = Optional.ofNullable(barriodao.findByNombreBarrio(barriodto.getNombreBarrio()));
            if (!barrioExists.isPresent()) {

                barriodao.insertBarrio(barrio);// Guardar el Barrio si no existe
                barmap.toDto(barrio);



            } else {
                barrio = barrioExists.get(); // Recuperar el Barrio existente
            }



            ubicacionDTO.setBarrio(barriodto); // Asociar el Barrio a la Ubicación
        }

        //Guardar la ubicacion
        ubidao.insertUbicacion(ubicacion);


        return ubicacionDTO;
    }

    @Override
    public UbicacionDTO updateUbicacion(UbicacionDTO ubicacion) {
        return null;
    }
}
