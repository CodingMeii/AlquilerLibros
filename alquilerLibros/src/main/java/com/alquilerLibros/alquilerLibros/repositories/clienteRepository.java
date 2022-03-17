package com.alquilerLibros.alquilerLibros.repositories;


import com.alquilerLibros.alquilerLibros.entyties.cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface clienteRepository extends JpaRepository<cliente, Integer> {
    public List<cliente> findAllById(int id);
    public List<cliente> findAll();
}
