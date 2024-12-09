package com.vecindapp.repository.dao;

import com.vecindapp.entity.Documento;
import com.vecindapp.entity.Tipodocumento;
import com.vecindapp.entity.Usuario;

import java.util.List;

public interface IDocumentoDAO {
    public List<Documento> guardarDoc(Documento documento);
    public Documento actualizarDoc(Documento documento);
    public List<Documento> buscarDocumentos();

    //Otros
    public List<Documento> findByUsuario(Usuario User);
    public List<Documento> findByTipoDocumento(Tipodocumento TipoDoc);
}
