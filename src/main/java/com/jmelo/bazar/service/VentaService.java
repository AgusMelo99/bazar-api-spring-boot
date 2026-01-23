package com.jmelo.bazar.service;

import com.jmelo.bazar.dto.ReporteVentasDto;
import com.jmelo.bazar.dto.VentaMayorDto;
import com.jmelo.bazar.model.DetalleVenta;
import com.jmelo.bazar.model.Producto;
import com.jmelo.bazar.model.Venta;
import com.jmelo.bazar.repository.VentaDetalleRepository;
import com.jmelo.bazar.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class VentaService implements IVentaService {

    @Autowired
    private VentaRepository ventRepo;
    @Autowired
    private VentaDetalleRepository detVentRepo;
    @Autowired
    private DetalleVentaService detVentServ;
   @Autowired
   private ProductoService prodServ;
    @Override
    public List<Venta> getVentas() {
        return ventRepo.findAll();
    }

    @Override
    public void saveVenta(Venta vent) {
        Venta ventProcesada = detVentServ.aprobarVenta(vent);
        ventRepo.save(ventProcesada);
    }

    @Override
    public List<String> saveVentas(List<Venta> ventas) {
        List<String> ventasResultado = new ArrayList<>();
            for (int i = 0; i < ventas.size(); i++) {
                Venta venta = ventas.get(i);
                int nroVenta = i + 1;
            try {
                this.saveVenta(venta);
                ventasResultado.add("Venta #" + nroVenta + " aprobada.");
            } catch (RuntimeException e) {
                ventasResultado.add("Venta #" + nroVenta + " rechazada. Motivo: " + e.getMessage());
            }
        }
        return ventasResultado;
    }

    @Override
    public void deleteVenta(Long id) {
        ventRepo.deleteById(id);
    }

    @Override
    public Venta findVenta(Long id) {
        return  ventRepo.findById(id).orElseThrow(()-> new RuntimeException("Venta inexistente"));
    }

    @Override
    @Transactional
    public void editVenta(Venta ventEdit) {
        Venta ventVieja = ventRepo.findById(ventEdit.getCodigo_venta()).orElseThrow(()-> new RuntimeException("Venta inexistente."));

        ventVieja.getlistaDetalleVentas().forEach(detalle ->{
            prodServ.actualizarStock(detalle.getProducto().getCodigoProducto(), detalle.getCantidad());
        });

        ventVieja.getlistaDetalleVentas().clear();

        Venta ventEditValida = detVentServ.aprobarVenta(ventEdit);
        //Actualizamos los datos existentes asi no se pisan y crean una nueva igual
        ventVieja.setFecha_venta(ventEditValida.getFecha_venta());
        ventVieja.setUnCliente(ventEditValida.getUnCliente());
        ventVieja.setTotal(ventEditValida.getTotal());

        ventVieja.getlistaDetalleVentas().addAll(ventEditValida.getlistaDetalleVentas());

        ventRepo.save(ventEditValida);
    }

    @Override
    public VentaMayorDto ventaMayor() {
        return ventRepo.findAll().stream()
                .max(Comparator.comparing(Venta::getTotal))
                .map(venta -> {
                    VentaMayorDto dto = new VentaMayorDto();
                    dto.setCodigo_venta(venta.getCodigo_venta());
                    dto.setTotal(venta.getTotal());
                    //tenemos que usar un metodo para sacar la cant de productos de una venta porque la cant de productos de una venta es la sumatoria de productos de todos los detalleVentas
                    dto.setCantProductos(this.cantProdVenta(venta.getCodigo_venta()));
                    dto.setNombreCliente(venta.getUnCliente().getNombre());
                    dto.setApellidoCliente(venta.getUnCliente().getApellido());
                    return dto;
                }).orElseThrow(() -> new RuntimeException("No hay ventas registradas."));
    }


    @Override
    public int cantProdVenta(Long idVenta) {

        Venta venta = ventRepo.findById(idVenta).orElse(null);
        if (venta == null) {
            return 0;
        }
        return venta.getlistaDetalleVentas().stream()
                .mapToInt(DetalleVenta::getCantidad)
                .sum();
    }

    @Override
    public List<Producto> getProductos(Long codigo_venta) {
        Venta venta = ventRepo.findById(codigo_venta).orElseThrow(()->new RuntimeException("Venta inexistente."));
        return venta.getlistaDetalleVentas().stream()
                .map(DetalleVenta::getProducto)
                .toList();
    }

    @Override
    public ReporteVentasDto getReporteDiario(LocalDate fecha) {
        List<Venta> ventasDelDia = ventRepo.findAll().stream()
                .filter(v ->v.getFecha_venta().isEqual(fecha))
                .toList();

        double montoTotalVentas = ventasDelDia.stream()
                .mapToDouble(Venta::getTotal)
                .sum();

        return new ReporteVentasDto(ventasDelDia.size(),montoTotalVentas);
    }
}





