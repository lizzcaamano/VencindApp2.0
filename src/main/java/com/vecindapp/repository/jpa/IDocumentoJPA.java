package com.vecindapp.repository.jpa;

import com.vecindapp.entity.Documento;
import com.vecindapp.entity.Tipodocumento;
import com.vecindapp.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IDocumentoJPA extends JpaRepository<Documento, Integer> {
    public List<Documento> findByUsuario(Usuario User);
    public List<Documento> findByTipoDocumento(Tipodocumento TipoDoc);
}
