package com.vecindapp.controller;

import com.vecindapp.entity.*;
import com.vecindapp.repository.dao.IChatDAO;
import com.vecindapp.repository.dao.IMensajeDAO;
import com.vecindapp.repository.dto.IMensajeMapper;
import com.vecindapp.repository.dto.IReservaMapper;
import com.vecindapp.repository.dto.MensajeDTO;
import com.vecindapp.repository.dto.ReservaDTO;
import com.vecindapp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
public class WebSocketController {

    @Autowired
    IMensajeService mensajeService;

    @Autowired
    IChatService chatService;

    @Autowired
    IPagoService pagoService;

    @Autowired
    IReservaService reservaService;

    @Autowired
    IMensajeDAO mensajeDAO;

    @Autowired
    IChatDAO chatDAO;

    @Autowired
    IReservaMapper reservaMapper;

    @Autowired
    IMensajeMapper mensajeMapper;

    @Autowired
    AuthService authService;


    @GetMapping("/user-info")
    public String getUserInfo() {
        // Obtén la autenticación del contexto de seguridad
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            // Accede a los detalles del usuario autenticado
            String username = authentication.getName(); // Nombre de usuario
            return "Usuario autenticado: " + username;
        } else {
            return "No se ha encontrado autenticación";}
    }


    @MessageMapping("/chat/{roomId}/send") // Cliente envía a /app/chat/{roomId}/send
    @SendTo("/topic/room/{roomId}") // Este mensaje será enviado a todos los suscriptores de /topic/room/{roomId}
    public MensajeDTO enviarMensaje(@DestinationVariable String roomId, MensajeDTO mensajeDTO) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            // Accede a los detalles del usuario autenticado
            String username = authentication.getName(); // Nombre de usuario
            System.out.println("Usuario autenticado: " + username);
        } else {
            System.out.println("No se ha encontrado autenticación");
        }



        // Validar que el roomId corresponde a un chat existente
        if (chatDAO.findChatByRoomId((roomId)) == null) {
            new RuntimeException("Room no encontrada");
        }

        // Guardar el mensaje utilizando el servicio de mensajes
        mensajeDTO = mensajeService.guardarMensaje(mensajeDTO);

        // Retornar el mensaje guardado para que sea enviado a los clientes suscritos a la sala
        return mensajeDTO;

    }


    @GetMapping("/{chatId}/mensajes")
    public ResponseEntity<List<MensajeDTO>> obtenerMensajes(@PathVariable Integer chatId) {
        List<MensajeDTO> mensajesDTO = mensajeService.listMensajesByChat(chatId);
        return ResponseEntity.ok(mensajesDTO);
    }


    @MessageMapping("/chat/{roomId}/confirmPrice")
    @SendTo("/topic/room/{roomId}")
    public MensajeDTO confirmarPrecio(
            @DestinationVariable String roomId,
            @Payload MensajeDTO mensajeDTO) {

        // Obtener el chat
        Chat chat = chatDAO.findChatByRoomId(roomId);
        if (chat == null) {
            throw new RuntimeException("Room no encontrada");
        }

        // Crear un nuevo pago con el precio acordado
        Pago pago = new Pago();
        pago.setMonto(mensajeDTO.getPrice());
        pago.setEstado("pendiente"); // Estado inicial
        pago.setFechaPago(Instant.now());  // Fecha de confirmación del precio
        pago.setTipoPago("");  // Este campo se completará más adelante

        // Guardar el pago

        pagoService.addPago(pago);

        // Crear un mensaje de precio acordado
        Mensaje mensaje = new Mensaje();
        mensaje.setChat(chat);
        mensaje.setContenido("Precio acordado: " + mensajeDTO.getPrice());
        mensaje.setFechaEnvio(Instant.now());
        mensajeDAO.save(mensaje);

        return mensajeDTO;
    }

    @MessageMapping("/chat/{roomId}/priceResponse")
    @SendTo("/topic/room/{roomId}")
    public MensajeDTO responderPrecio(
            @DestinationVariable String roomId,
            @Payload MensajeDTO mensajeDTO) {

        // Obtener el chat
        Chat chat = chatDAO.findChatByRoomId(roomId);
        if (chat == null) {
            throw new RuntimeException("Room no encontrada");
        }

        if (mensajeDTO.getEsSolicitudPrecio()) {

            // Crear la reserva con el precio del pago
            Pago pago = pagoService.findByMonto(mensajeDTO.getPrice());

            if (pago == null) {
                throw new RuntimeException("Pago no encontrado");
            }
            // Crear la reserva
            Reserva reserva = new Reserva();
            reserva.setUsuario(chat.getUsuario());  // Cliente
            reserva.setServicio(chat.getServicio());  // Servicio
            reserva.setPago(pago);  // Asociar el pago con la reserva
            reserva.setEstado("pendiente");// Estado inicial de la reserva

            ReservaDTO reservaDTO = reservaMapper.toDTO(reserva);

            reservaService.addReserva(reservaDTO);

            // Actualizar el mensaje de precio
            Mensaje mensaje = new Mensaje();
            mensaje.setContenido("Precio aceptado: " + mensajeDTO.getPrice());
            mensajeDAO.save(mensaje);

            mensajeDTO.setReservaId(reserva.getId());  // Agregar ID de la reserva
        } else {
            // Si el trabajador rechaza el precio
            Mensaje mensaje = new Mensaje();
            mensaje.setContenido("Precio rechazado");
            mensajeDAO.save(mensaje);
        }

        return mensajeDTO;
    }

    @PostMapping("/chat/{roomId}/priceResponses")
    public ResponseEntity<MensajeDTO> responderPrecioREST(
            @PathVariable String roomId,
            @RequestBody MensajeDTO mensajeDTO) {
        return ResponseEntity.ok(responderPrecio(roomId, mensajeDTO));
    }
}
