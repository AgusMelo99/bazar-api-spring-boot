package com.jmelo.bazar.service;

import com.jmelo.bazar.dto.ReporteVentasDto;
import com.jmelo.bazar.dto.VentaMayorDto;
import com.jmelo.bazar.model.Producto;
import com.jmelo.bazar.model.Venta;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface IVentaService {
    List<Venta> getVentas();

    void saveVenta(Venta vent);

    List<String> saveVentas(List<Venta> ventas);

    void deleteVenta(Long id);

    Venta findVenta(Long id);

    void editVenta(Venta vent);

    VentaMayorDto ventaMayor();

    int cantProdVenta(Long idVenta);

    List<Producto> getProductos(Long codigo_venta);

    ReporteVentasDto getReporteDiario(LocalDate fecha);
}
