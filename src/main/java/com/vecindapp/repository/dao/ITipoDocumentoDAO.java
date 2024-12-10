package com.vecindapp.repository.dao;

import com.vecindapp.entity.Tipodocumento;

import java.util.List;

public interface ITipoDocumentoDAO {
    public List<Tipodocumento> buscarTipoDocumento();
    public Tipodocumento findByNombre(String nombre);
}
