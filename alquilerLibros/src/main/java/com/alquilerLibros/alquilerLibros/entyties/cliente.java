package com.alquilerLibros.alquilerLibros.entyties;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.annotation.processing.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class cliente {
    @Id
    private int id;

    @NotNull
    private String nombre;

    @NotNull
    private String apellido;

    @NotNull
    private String email;

    @NotNull
    private String clave;

    @NotNull
    private boolean tipoUsuario;

    @NotNull
    private boolean estado;
}
