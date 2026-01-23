package com.jmelo.bazar.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;


@Entity
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int cantidad;
    private double subtotal;

    @ManyToOne
    @JoinColumn(name = "producto_codigo_producto")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "venta_codigo_venta")
    @JsonBackReference
    private Venta venta;

    public DetalleVenta() {
    }

    public DetalleVenta(Venta venta, Producto producto, int cantidad, double subtotal) {
        this.venta = venta;
        this.producto = producto;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
}