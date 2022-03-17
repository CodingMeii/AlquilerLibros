package com.alquilerLibros.alquilerLibros.entyties;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class libro {
    @Id
    private Integer id;

    @NotNull
    private String nombre;

    @NotNull
    private String autor;

    @NotNull
    private int nEjemplares;

    @NotNull
    private int nDisponible;

    @NotNull
    private int nReservada;

    @NotNull
    private float vAlquiler;
}
