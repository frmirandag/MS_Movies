package com.example.bdget.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "movies")
public class Pelicula{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "El título de la película no puede estar vacío")
    @Column(name = "title")
    private String titulo;

    @Column(name = "year")
    private int año;

    @NotBlank(message = "El director de la película no puede estar vacío")
    @Column(name = "director")
    private String director;

    @NotBlank(message = "El género de la película no puede estar vacío")
    @Column(name = "gender")
    private String genero;

    @NotBlank(message = "La sinopsis de la película no puede estar vacía")
    @Column(name = "synopsis", length = 600)
    private String sinopsis;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id2) {
        this.id = (long) id2;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getYear() {
        return año;
    }

    public void setYear(int año) {
        this.año = año;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }


}
