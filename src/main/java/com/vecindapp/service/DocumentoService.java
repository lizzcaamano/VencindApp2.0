package com.vecindapp.service;

import com.vecindapp.entity.Documento;
import com.vecindapp.entity.Tipodocumento;
import com.vecindapp.entity.Usuario;
import com.vecindapp.repository.dao.IDocumentoDAO;
import com.vecindapp.repository.dao.ITipoDocumentoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentoService implements IDocumentoService {

    @Autowired
    IDocumentoDAO docdao;

    @Autowired
    ITipoDocumentoDAO tipodao;

    @Override
    public List<Documento> guardarDoc(Documento documento) {
        Tipodocumento tipodocumento = tipodao.findByNombre(documento.getTipoDocumento().getNombre());
        documento.setTipoDocumento(tipodocumento);
        docdao.guardarDoc(documento);
        return ListarDocumentos();
    }

    @Override
    public Documento actualizarDoc(Documento documento) {
        return null;
    }

    @Override
    public List<Documento> buscarDocumentos() {
        return List.of();
    }

    @Override
    public List<Documento> ListarDocumentos() {
        return docdao.ListarDocumentos();
    }

    @Override
    public List<Documento> findByUsuario(Usuario User) {
        return List.of();
    }

    @Override
    public List<Documento> findByTipoDocumento(Tipodocumento TipoDoc) {
        return List.of();
    }
}
