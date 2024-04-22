package com.example.bdget.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bdget.model.Pelicula;
import com.example.bdget.service.PeliculaService;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/peliculas")
public class PeliculaController {

    private static final Logger log = LoggerFactory.getLogger(PeliculaController.class);

    @Autowired
    private PeliculaService peliculaService;

    @GetMapping
    public List<Pelicula> getAllPeliculas() {
        log.info("GET /peliculas");
        log.info("Retornando todas las películas");
        return peliculaService.getAllPeliculas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPeliculaById(@PathVariable @NotNull @Min(1) Long id) {
        Optional<Pelicula> pelicula = peliculaService.getPeliculaById(id);
        if (pelicula.isEmpty()) {
            log.error("No se encontró la película con ID {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("No se encontró la película con ID " + id));
        }
        return ResponseEntity.ok(pelicula);
    }

    @PostMapping
    public ResponseEntity<Object> createPelicula(@Valid @RequestBody Pelicula pelicula) {
        Pelicula createdPelicula = peliculaService.createPelicula(pelicula);
        if (createdPelicula == null) {
            log.error("Error al crear la película {}", pelicula);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Error al crear la película"));
        }
        return ResponseEntity.ok(createdPelicula);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePelicula(@PathVariable @NotNull @Min(1) Long id, @Valid @RequestBody Pelicula pelicula) {
        Pelicula updatedPelicula = peliculaService.updatePelicula(id, pelicula);
        if (updatedPelicula == null) {
            log.error("No se encontró la película con ID {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("No se encontró la película con ID " + id));
        }
        return ResponseEntity.ok(updatedPelicula);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePelicula(@PathVariable @NotNull @Min(1) Long id) {
        peliculaService.deletePelicula(id);
        return ResponseEntity.ok().build();
    }

    static class ErrorResponse {
        private final String message;

        public ErrorResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
 