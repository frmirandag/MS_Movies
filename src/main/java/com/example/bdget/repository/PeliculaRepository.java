package com.example.bdget.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bdget.model.Pelicula;

public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {
    
}
