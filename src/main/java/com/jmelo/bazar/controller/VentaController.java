package com.jmelo.bazar.controller;

import com.jmelo.bazar.dto.ReporteVentasDto;
import com.jmelo.bazar.dto.VentaMayorDto;
import com.jmelo.bazar.model.Producto;
import com.jmelo.bazar.model.Venta;
import com.jmelo.bazar.service.IDetalleVentaService;
import com.jmelo.bazar.service.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class VentaController {

    @Autowired
    private IVentaService ventService;
    @Autowired
    private IDetalleVentaService detalleVentaService;

    @GetMapping("/bazar/ventas")
    public List<Venta> getventas() {
        return ventService.getVentas();
    }

    @GetMapping("/bazar/ventas/{codigo_venta}")
    public Venta findVenta(@PathVariable Long codigo_venta) {
        return ventService.findVenta(codigo_venta);
    }

    @PostMapping("/bazar/ventas/crear")
    public List<String> crearVenta(@RequestBody List<Venta> vents) {
        return ventService.saveVentas(vents);
    }

    @DeleteMapping("/bazar/ventas/delete/{id_venta}")
    public String deleteVenta(@PathVariable Long id_venta) {
        ventService.deleteVenta(id_venta);
        return "Venta borrado exitosamente";
    }

    @PutMapping("bazar/ventas/edit")
    public String editVenta(@RequestBody Venta vent) {
        ventService.editVenta(vent);
        return "Venta actualizado correctamente";
    }

    @GetMapping("bazar/ventas/productos/{codigo_venta}")
    public List<Producto> listaProdutos(@PathVariable Long codigo_venta) {
        return ventService.getProductos(codigo_venta);
    }

    //Obtener la sumatoria del monto y también cantidad total de ventas de un determinado
    //día
    @GetMapping("bazar/ventas/dia/{fecha_venta}")
    public ReporteVentasDto montoPorFecha(@PathVariable LocalDate fecha_venta) {
        return ventService.getReporteDiario(fecha_venta);
    }

    @GetMapping("bazar/ventas/mayor_venta")
    public VentaMayorDto mayorVenta() {
        return ventService.ventaMayor();
    }
}
