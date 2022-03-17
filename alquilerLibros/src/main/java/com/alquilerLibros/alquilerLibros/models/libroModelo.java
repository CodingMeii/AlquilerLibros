package com.alquilerLibros.alquilerLibros.models;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class libroModelo {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("autor")
    @Expose
    private String autor;
    @SerializedName("nEjemplares")
    @Expose
    private Integer nEjemplares;
    @SerializedName("nDisponible")
    @Expose
    private Integer nDisponible;
    @SerializedName("nReserva")
    @Expose
    private Integer nReserva;
    @SerializedName("valorAlquiler")
    @Expose
    private Double valorAlquiler;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Integer getnEjemplares() {
        return nEjemplares;
    }

    public void setnEjemplares(Integer nEjemplares) {
        this.nEjemplares = nEjemplares;
    }

    public Integer getnDisponible() {
        return nDisponible;
    }

    public void setnDisponible(Integer nDisponible) {
        this.nDisponible = nDisponible;
    }

    public Integer getnReserva() {
        return nReserva;
    }

    public void setnReserva(Integer nReserva) {
        this.nReserva = nReserva;
    }

    public Double getvAlquiler() {
        return valorAlquiler;
    }

    public void setvAlquiler(Double valorAlquiler) {
        this.valorAlquiler = valorAlquiler;
    }

}