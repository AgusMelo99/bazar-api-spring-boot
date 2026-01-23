package com.jmelo.bazar.dto;

public class VentaMayorDto {

    private Long codigo_venta;
    private double total;
    private int cantProductos;
    private String nombreCliente;
    private String apellidoCliente;

    public VentaMayorDto(Long codigo_venta, double total, int cantProductos, String nombreCliente, String apellidoCliente) {
        this.codigo_venta = codigo_venta;
        this.total = total;
        this.cantProductos = cantProductos;
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
    }

    public VentaMayorDto() {
    }

    public void setApellidoCliente(String apellidoCliente) {
        this.apellidoCliente = apellidoCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public void setCantProductos(int cantProductos) {
        this.cantProductos = cantProductos;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setCodigo_venta(Long codigo_venta) {
        this.codigo_venta = codigo_venta;
    }

    public Long getCodigo_venta() {
        return codigo_venta;
    }

    public double getTotal() {
        return total;
    }

    public int getCantProductos() {
        return cantProductos;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public String getApellidoCliente() {
        return apellidoCliente;
    }
}
