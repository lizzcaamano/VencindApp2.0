package com.vecindapp.service;

import com.vecindapp.entity.Categoria;
import com.vecindapp.entity.Servicio;
import com.vecindapp.repository.dao.ICategoriaDAO;
import com.vecindapp.repository.dao.IServicioDAO;
import com.vecindapp.repository.dto.IServicioMapper;
import com.vecindapp.repository.dto.ServicioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicioService implements IServicioService{

    @Autowired
    IServicioDAO servicioDAO;

    @Autowired
    IServicioMapper servicioMapper;

    @Autowired
    ICategoriaDAO categoriaDAO;

    @Override
    @Transactional
    public List<ServicioDTO> addServicio(ServicioDTO servicioDTO) {

        //Mapear DTO a entidad para posteriormente guardar
        Servicio servicio = servicioMapper.toEntity(servicioDTO);

        //validar que exista la categoria si se pone el nombre y setearla
        if(servicioDTO.getCategoriaName() != null){
            Categoria categoria = categoriaDAO.findNameCategoria(servicioDTO.getCategoriaName());
            if (categoria == null){
                throw new RuntimeException("Categoría no encontrada con nombre: " + servicioDTO.getCategoriaName());
            }
            servicio.setCategoria(categoria);
        }

        //guardamos el servicio
        servicioDAO.InsertServicio(servicio);

        return listAllServicios();


    }

    @Override
    public ServicioDTO UpdServicio(Integer id, ServicioDTO servicioDTO) {
        return null;
    }

    @Override
    public Servicio FindIdServicio(int id) {
        return null;
    }

    @Override
    public List<ServicioDTO> listAllServicios() {
        //obtener entidades desde repositorio
        List<Servicio> servicios = servicioDAO.listServicios();

        //De entidades a DTO´s
        List<ServicioDTO> servicioDTOS = servicios.stream().
                map(servicioMapper::toDTO).collect(Collectors.toList());

        return servicioDTOS;
    }
}
