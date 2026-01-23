package com.jmelo.bazar.dto;

public class ReporteVentasDto {
    private int cantidadVentas;
    private double montoTotal;

    public ReporteVentasDto() {}

    public ReporteVentasDto(int cantidadVentas, double montoTotal) {
        this.cantidadVentas = cantidadVentas;
        this.montoTotal = montoTotal;
    }

    // Getters y Setters
    public int getCantidadVentas() {
        return cantidadVentas;
    }
    public void setCantidadVentas(int cantidadVentas) {
        this.cantidadVentas = cantidadVentas;
    }
    public double getMontoTotal() {
        return montoTotal;
    }
    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }
}