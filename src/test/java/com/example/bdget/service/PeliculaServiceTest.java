package com.example.bdget.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.bdget.model.Pelicula;
import com.example.bdget.repository.PeliculaRepository;

@ExtendWith(MockitoExtension.class)
public class PeliculaServiceTest {

    @InjectMocks
    private PeliculaServiceImpl peliculaService;

    @Mock
    private PeliculaRepository peliculaRepositoryMock;

    @Test
    public void testCrearPelicula() {
        // Se crea una instancia de Pelicula para simular los datos de entrada
        Pelicula peliculaEntrada = new Pelicula();
        peliculaEntrada.setTitulo("Titanic");
        peliculaEntrada.setYear(1997);
        peliculaEntrada.setDirector("James Cameron");

        // Se configura el comportamiento esperado del repositorio mock
        when(peliculaRepositoryMock.save(any())).thenReturn(peliculaEntrada);

        // Se invoca el método del servicio que se está probando
        Pelicula peliculaCreada = peliculaService.createPelicula(peliculaEntrada);

        // Se verifica que la película creada tenga los mismos atributos que la película de entrada
        assertEquals(peliculaEntrada.getTitle(), peliculaCreada.getTitle());
        assertEquals(peliculaEntrada.getYear(), peliculaCreada.getYear());
        assertEquals(peliculaEntrada.getDirector(), peliculaCreada.getDirector());
    }

    @Test
    public void testObtenerPeliculaPorIdExistente() {
        // Se crea una instancia de Pelicula para simular los datos de la película existente
        Pelicula peliculaExistente = new Pelicula();
        peliculaExistente.setId(1L);
        peliculaExistente.setTitulo("El Padrino");

        // Se configura el comportamiento esperado del repositorio mock para devolver la película existente
        when(peliculaRepositoryMock.findById(1L)).thenReturn(Optional.of(peliculaExistente));

        // Se invoca el método del servicio que se está probando para obtener la película por ID
        Optional<Pelicula> peliculaOpt = peliculaService.getPeliculaById(1L);

        // Se verifica que se obtenga la película correctamente y que tenga el título esperado
        assertTrue(peliculaOpt.isPresent());
        assertEquals("El Padrino", peliculaOpt.get().getTitle());
    }

    @Test
    public void testObtenerPeliculaPorIdNoExistente() {
        // Se configura el comportamiento esperado del repositorio mock para devolver un Optional vacío
        when(peliculaRepositoryMock.findById(2L)).thenReturn(Optional.empty());

        // Se invoca el método del servicio que se está probando para obtener la película por un ID no existente
        Optional<Pelicula> peliculaOpt = peliculaService.getPeliculaById(2L);

        // Se verifica que no se obtenga ninguna película
        assertTrue(peliculaOpt.isEmpty());
    }
}
