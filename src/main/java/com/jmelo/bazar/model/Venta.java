package com.jmelo.bazar.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;
@Entity
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long codigo_venta;
    private LocalDate fecha_venta;
    private Double total = 0.0;
    @OneToMany(mappedBy = "venta",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<DetalleVenta> listaDetalleVentas;
    @ManyToOne
    private Cliente unCliente;

    public Venta(LocalDate fecha_venta,  List<DetalleVenta> listaDetalleVentas, Cliente unCliente) {
        this.fecha_venta = fecha_venta;
        this.total = 0.0;
        this.listaDetalleVentas = listaDetalleVentas;
        this.unCliente = unCliente;
    }

    public Venta() {
    }

    public void setCodigo_venta(Long codigo_venta) {
        this.codigo_venta = codigo_venta;
    }

    public void setFecha_venta(LocalDate fecha_venta) {
        this.fecha_venta = fecha_venta;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public void setlistaDetalleVentas(List<DetalleVenta> listaDetalleVentas) {
        this.listaDetalleVentas = listaDetalleVentas;
    }

    public void setUnCliente(Cliente unCliente) {
        this.unCliente = unCliente;
    }

    public Long getCodigo_venta() {
        return codigo_venta;
    }

    public LocalDate getFecha_venta() {
        return fecha_venta;
    }

    public Double getTotal() {
        return total;
    }

    public List<DetalleVenta> getlistaDetalleVentas() {
        return listaDetalleVentas;
    }

    public Cliente getUnCliente() {
        return unCliente;
    }
}
