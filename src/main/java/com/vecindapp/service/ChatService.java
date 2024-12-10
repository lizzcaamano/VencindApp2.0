package com.vecindapp.service;

import com.vecindapp.entity.Chat;
import com.vecindapp.entity.Servicio;
import com.vecindapp.entity.Usuario;
import com.vecindapp.repository.dao.IChatDAO;
import com.vecindapp.repository.dao.IServicioDAO;
import com.vecindapp.repository.dao.IUsuarioDAO;
import com.vecindapp.repository.dao.IUsuarioRolDAO;
import com.vecindapp.repository.dto.ChatDTO;
import com.vecindapp.repository.dto.IChatMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class ChatService implements IChatService{

    @Autowired
    IChatDAO chatDAO;

    @Autowired
    IUsuarioRolDAO usuarioRolDAO;

    @Autowired
    IUsuarioDAO usuarioDAO;

    @Autowired
    IServicioDAO servicioDAO;

    @Autowired
    IChatMapper chatMapper;

    @Override
    public ChatDTO obtenerChatConRoles(Integer chatId) {

        Chat chat = chatDAO.findById(chatId);
        if(chat == null){
            new RuntimeException("Chat no encontrado");
        }

        // Determinar los roles del cliente
        String rolCliente = obtenerRolPorUsuario(chat.getUsuario());

        // Determinar los roles del trabajador (suponiendo que el trabajador es un usuario diferente asociado al chat)
        String rolTrabajador = obtenerRolPorUsuario(chat.getTrabajador());

        //mapear
        // Usar mapper para convertir Chat a ChatDTO
        ChatDTO chatDTO = chatMapper.toDTO(chat);

        System.out.println(chat);

        // Asignar roles al DTO
        chatDTO.setClienteRol(rolCliente);
        chatDTO.setTrabajadorRol(rolTrabajador);

        return chatDTO;
    }

    @Override
    public ChatDTO addChat(Integer usuarioId, Integer trabajadorId, Integer servicioId) {

        Usuario cliente = usuarioDAO.findById(usuarioId);
        Usuario trabajador = usuarioDAO.findById(trabajadorId);
        Servicio servicio = servicioDAO.findIdServicio(servicioId);

        // Generar roomId único
        String roomId = UUID.randomUUID().toString();

        // Crear un nuevo Chat
        Chat chat = new Chat();
        chat.setUsuario(cliente);
        chat.setTrabajador(trabajador);
        chat.setServicio(servicio);
        chat.setRoomId(roomId);
        chat.setFechaInicio(Instant.now());

        chat = chatDAO.InsertChat(chat);

        return chatMapper.toDTO(chat);
    }

    private String obtenerRolPorUsuario(Usuario usuario) {

        // Asegúrate de que el objeto 'usuario' no sea nulo
        if (usuario == null) {
            return "Desconocido";
        }

        List<String> roles = usuarioRolDAO.findUsuarioRolByUsuarioId(usuario.getId());

        if (roles.contains("Cliente")) {
            return "Cliente";
        } else if (roles.contains("Trabajador")) {
            return "Trabajador";
        } else {
            return "Desconocido";  // Si el usuario no tiene un rol reconocido
        }
    }

    @Override
    public ChatDTO findByRoomId(String roomId) {

        // Buscar el chat por roomId en el DAO
        Chat chat = chatDAO.findChatByRoomId(roomId);

        if (chat == null) {
            new RuntimeException("Chat no encontrado para roomId: " + roomId);
        }

        // Convertir el Chat a ChatDTO usando un mapper
        ChatDTO chatDTO = chatMapper.toDTO(chat);

        return chatDTO;
    }
}
