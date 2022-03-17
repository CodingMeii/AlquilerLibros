package com.alquilerLibros.alquilerLibros.repositories;

import com.alquilerLibros.alquilerLibros.entyties.libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface libroRepository extends JpaRepository<libro, Integer> {

    public List<libro> findAllById(int id);
    public List<libro> findAll();
}