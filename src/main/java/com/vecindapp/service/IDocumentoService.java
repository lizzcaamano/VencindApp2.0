package com.vecindapp.service;

import com.vecindapp.entity.Documento;
import com.vecindapp.entity.Tipodocumento;
import com.vecindapp.entity.Usuario;

import java.util.List;

public interface IDocumentoService {
    public List<Documento> guardarDoc(Documento documento);
    public Documento actualizarDoc(Documento documento);
    public List<Documento> buscarDocumentos();
    public List<Documento>ListarDocumentos();

    //Otros
    public List<Documento> findByUsuario(Usuario User);
    public List<Documento> findByTipoDocumento(Tipodocumento TipoDoc);
}
