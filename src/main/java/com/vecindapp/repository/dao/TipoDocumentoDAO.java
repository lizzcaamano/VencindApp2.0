package com.vecindapp.repository.dao;

import com.vecindapp.entity.Tipodocumento;
import com.vecindapp.repository.jpa.ITipoDocumentoJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TipoDocumentoDAO implements ITipoDocumentoDAO {

    @Autowired
    ITipoDocumentoJPA tipdocjpa;

    @Override
    public List<Tipodocumento> buscarTipoDocumento() {
        return tipdocjpa.findAll();
    }

    @Override
    public Tipodocumento findByNombre(String nombre) {
        return tipdocjpa.findByNombre(nombre);
    }
}
