package com.example.bdget.controller;


import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.bdget.model.Pelicula;
import com.example.bdget.service.PeliculaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

@WebMvcTest(PeliculaController.class)
public class PeliculaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PeliculaService peliculaService;

    @Test
    public void testGetAllPeliculas() throws Exception {
        // Datos de prueba
        Pelicula pelicula1 = new Pelicula();
        pelicula1.setId(1L);
        pelicula1.setTitulo("Titanic");

        Pelicula pelicula2 = new Pelicula();
        pelicula2.setId(2L);
        pelicula2.setTitulo("Avatar");

        List<Pelicula> peliculas = Arrays.asList(pelicula1, pelicula2);

        // Mock del servicio
        when(peliculaService.getAllPeliculas()).thenReturn(peliculas);

        // Ejecución de la solicitud HTTP y verificación de los resultados
        mockMvc.perform(get("/peliculas")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].titulo").value("Titanic"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].titulo").value("Avatar"));
    }
}
