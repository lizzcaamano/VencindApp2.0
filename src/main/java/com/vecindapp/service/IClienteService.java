package com.vecindapp.service;

import com.vecindapp.repository.dto.ClienteDTO;
import com.vecindapp.repository.dto.UsuarioDTO;

import java.util.List;

public interface IClienteService {
    public List<ClienteDTO> ListClientes();
    public List<ClienteDTO> insertCliente(ClienteDTO ClienteDTO);
    public ClienteDTO updateCliente(Integer id, ClienteDTO clienteDTO);

    //CONSULTAS ADICIONALES
    public List<ClienteDTO> findByNombre(String nombre);
    //public List<Usuario> findByCalificacion(Calificacion calificaciones);
    //public Usuario updateByEstado(String estado);
}
