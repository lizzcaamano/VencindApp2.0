package com.vecindapp.service;

import com.vecindapp.entity.Chat;
import com.vecindapp.entity.Mensaje;
import com.vecindapp.repository.dao.IChatDAO;
import com.vecindapp.repository.dao.IMensajeDAO;
import com.vecindapp.repository.dto.IMensajeMapper;
import com.vecindapp.repository.dto.MensajeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MensajeService implements IMensajeService {

    @Autowired
    IMensajeDAO mensajeDAO;

    @Autowired
    IChatDAO chatDAO;

    @Autowired
    IMensajeMapper mensajeMapper;

    @Override
    public MensajeDTO guardarMensaje(MensajeDTO mensajeDTO) {
        Mensaje mensaje = new Mensaje();
        mensaje.setContenido(mensajeDTO.getContenido());
        mensaje.setFechaEnvio(Instant.now());

        // Lógica para asociar el mensaje con el chat
        // Suponiendo que el mensajeDTO ya tiene el chatId
        mensaje.setChat(chatDAO.findById(mensajeDTO.getChatId())); // Obtener el chat por ID

        //determinar el rol
        Chat chat = mensaje.getChat();
        String senderRole;

        if (mensajeDTO.getChatId().equals(chat.getUsuario().getId())) {
            senderRole = "Cliente";  // Si el mensaje lo envía el cliente
        } else if (mensajeDTO.getChatId().equals(chat.getTrabajador().getId())) {
            senderRole = "Trabajador";  // Si el mensaje lo envía el trabajador
        } else {
            senderRole = "Desconocido"; // O manejo de error si no coincide con ninguno de los dos
        }

        // Guardar el mensaje
        Mensaje mensajeGuardado = mensajeDAO.save(mensaje);

        // Mapear el mensaje guardado al DTO
        mensajeDTO.setId(mensajeGuardado.getId());
        mensajeDTO.setFechaEnvio(mensajeGuardado.getFechaEnvio());
        mensajeDTO.setSenderRole(senderRole);

        return mensajeDTO;
    }

    @Override
    public List<MensajeDTO> listMensajesByChat(int chatId) {
        // Obtener todos los mensajes del chat
        List<Mensaje> mensajes = mensajeDAO.findByChatId(chatId);

        // Convertir los mensajes a DTOs
        return mensajes.stream()
                .map(mensaje -> {
                    MensajeDTO dto = mensajeMapper.toDTO(mensaje);
                    Chat chat = mensaje.getChat();

                    // Asignar el rol del remitente
                    if (mensaje.getChat().getId().equals(chat.getUsuario().getId())) {
                        dto.setSenderRole("Cliente");
                    } else {
                        dto.setSenderRole("Trabajador");
                    }
                    return dto;
                }

                 )
                .collect(Collectors.toList());
    }
}
