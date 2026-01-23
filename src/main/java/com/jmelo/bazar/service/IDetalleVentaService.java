package com.jmelo.bazar.service;

import com.jmelo.bazar.model.DetalleVenta;
import com.jmelo.bazar.model.Venta;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IDetalleVentaService {

    Venta aprobarVenta(Venta venta);
    List<DetalleVenta> getDetVentas();
}
