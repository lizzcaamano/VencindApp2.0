package com.vecindapp.vecindappv2;

import com.vecindapp.entity.Agenda;
import com.vecindapp.repository.dto.AgendaDTO;
import com.vecindapp.service.AgendaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.ZonedDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest // Carga el contexto de Spring Boot.
class AgendaServiceTest {

    @Autowired
    private AgendaService agendaService;

    @Test
    void testGetAgenda() {
        // Actuar
        List<Agenda> agenda = agendaService.listAllAgendas();

        // Verificar
        assertThat(agenda).isNotNull(); // Asegura que no sea nulo.
        assertThat(agenda).hasSize(4); // Asegura que tiene 3 elementos.
    }

    @BeforeEach
    void setUp() {
        agendaService.listAllAgendas().clear();
    }

    //TODO:Arreglar Test Agenda :)
    @Test
    void testAddAgenda() {
        // Preparar
        /*AgendaDTO newItem = new AgendaDTO(
                "pendiente",
                "pintar pared",
                ZonedDateTime.parse("2024-12-01T09:00:00Z").toInstant(),
                ZonedDateTime.parse("2024-12-01T10:00:00Z").toInstant(),
                2,
                3
        );

        // Actuar
        agendaService.addAgenda(newItem);
        List<Agenda> agenda = agendaService.listAllAgendas();

        // Verificar
        assertThat(agenda).isNotNull();
        assertThat(agenda).hasSize(10); // Debe contener exactamente un elemento.*/
    }
}
