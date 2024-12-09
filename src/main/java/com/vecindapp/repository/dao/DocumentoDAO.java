package com.vecindapp.repository.dao;

import com.vecindapp.entity.Documento;
import com.vecindapp.entity.Tipodocumento;
import com.vecindapp.entity.Usuario;
import com.vecindapp.repository.jpa.IDocumentoJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DocumentoDAO implements IDocumentoDAO{

    @Autowired
    IDocumentoJPA docjpa;
    @Autowired
    private JpaContext jpaContext;

    @Override
    public List<Documento> guardarDoc(Documento documento) {
        docjpa.save(documento);
        return buscarDocumentos();
    }

    @Override
    public Documento actualizarDoc(Documento documento) {
        return docjpa.save(documento);
    }

    @Override
    public List<Documento> buscarDocumentos() {
        return docjpa.findAll();
    }

    @Override
    public List<Documento> findByUsuario(Usuario User) {
        return docjpa.findByUsuario(User);
    }

    @Override
    public List<Documento> findByTipoDocumento(Tipodocumento TipoDoc) {
        return docjpa.findByTipoDocumento(TipoDoc);
    }
}
