package com.vecindapp.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

    //la anotacion @configuration se va a ejecutar netamente cuando la aplicacion este levantada
    //la anotacion @EnableWebSocketMessageBroker nos permitira configurar un brocker para la comunicacion con los clientes

    //implementacion de metodos

    //BROCKER - es una manera de comunicarnos desde el back al front - cuando se trabaja con websocket se utiliza un brocker
    //Este metodo nos permite habilitar un brocker
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //aqui habilitamos el brocker e indicamos el path o ruta
        registry.enableSimpleBroker("/topic");
        //establecer un path de destino de los mensajes
        registry.setApplicationDestinationPrefixes("/app");
    }

    //este metodo nos permite registrar los endpoints
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //permite indicarle al front con que path va a conectarse al servidor socket
        //con el setAllowed mapeamos que clientes pueden conectarse al endpoint 4200 (angular)
        //conexion mediante WithSockJS (libreria)
        registry.addEndpoint("/chat-socket")
                .setAllowedOriginPatterns("*")
                .withSockJS();
    }

}
