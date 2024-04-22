package com.example.bdget.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.bdget.model.Pelicula;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PeliculaRepositoryTest {

    @Autowired
    private PeliculaRepository peliculaRepository;

    @Test
    public void guardarPeliculaTest() {
        // Crear una película
        Pelicula pelicula = new Pelicula();
        pelicula.setTitulo("Titanic 2");
        pelicula.setYear(1997);
        pelicula.setDirector("James Cameron");
        pelicula.setGenero("Romance");
        pelicula.setSinopsis("Una historia de amor en el desafortunado viaje inaugural del RMS Titanic.");
        // Guardar la película en la base de datos
        Pelicula resultado = peliculaRepository.save(pelicula);

        // Verificar que se haya asignado un ID y que los detalles de la película sean correctos
        assertNotNull(resultado.getId());
        assertEquals("Titanic 2", resultado.getTitulo());
        assertEquals(1997, resultado.getYear());
        assertEquals("James Cameron", resultado.getDirector());
        assertEquals("Romance", resultado.getGenero());
        assertEquals("Una historia de amor en el desafortunado viaje inaugural del RMS Titanic.", resultado.getSinopsis());
    }
}