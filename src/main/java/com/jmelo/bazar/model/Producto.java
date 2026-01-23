package com.jmelo.bazar.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long codigoProducto;
    private String nombre;
    private String marca;
    private Double costo;
    private Double cantidadDisponible;

    public Producto() {
    }

    public Producto(Long codigoProducto, String nombre, String marca, Double costo, Double cantidadDisponible) {
        this.codigoProducto = codigoProducto;
        this.nombre = nombre;
        this.marca = marca;
        this.costo = costo;
        this.cantidadDisponible = cantidadDisponible;
    }

    public Long getCodigoProducto() {
        return codigoProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMarca() {
        return marca;
    }

    public Double getCosto() {
        return costo;
    }

    public Double getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCodigoProducto(Long codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public void setCantidadDisponible(Double cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }
}
