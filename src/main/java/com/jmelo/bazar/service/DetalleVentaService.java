package com.jmelo.bazar.service;

import com.jmelo.bazar.model.DetalleVenta;
import com.jmelo.bazar.model.Venta;
import com.jmelo.bazar.model.Producto;
import com.jmelo.bazar.repository.ProductoRepository;
import com.jmelo.bazar.repository.VentaDetalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DetalleVentaService implements IDetalleVentaService {

    @Autowired
    private VentaDetalleRepository detVentRepo;
    @Autowired
    private IProductoService prodService;
    @Autowired
    private ProductoRepository prodRepo;

    @Override
    @Transactional
    public Venta aprobarVenta(Venta venta) {
        List<DetalleVenta> detalleVentas = venta.getlistaDetalleVentas();
        //Validamos que tengamos stock suficiente para cada producto
        detalleVentas.forEach(detalleVenta -> {
            Long id_prod = detalleVenta.getProducto().getCodigoProducto();
            Producto prod = prodRepo.findById(id_prod)
                    .orElseThrow(() -> new RuntimeException("Producto ID" + id_prod + "no existe"));
            //Nos fijamos que haya stock
            if (!prodService.hayStock(id_prod, detalleVenta.getCantidad())) {
                throw new RuntimeException("Stock insuficiente para: " + prod.getNombre());
            }
        });
        double totalVenta = detalleVentas.stream()
                .mapToDouble(detalleVenta -> {
                    //busco si el producto que me mandan por la venta existe
                    Producto prod = prodRepo.findById(detalleVenta.getProducto().getCodigoProducto()).orElseThrow(() -> new RuntimeException("Producto no encontrado al momento de validar la compra"));
                    detalleVenta.setProducto(prod);
                    detalleVenta.setVenta(venta);
                    //despues de setear el producto completo y la venta calculamos el subtotal de la venta por cada producto
                    double subtotal = prod.getCosto() * detalleVenta.getCantidad();
                    detalleVenta.setSubtotal(subtotal);
                    //aca solo actualizo el stock de cada producto por si lo necesito en otra venta
                    prodService.actualizarStock(prod.getCodigoProducto(), -detalleVenta.getCantidad());

                    return subtotal;
                })
                .sum();
                venta.setTotal(totalVenta);
        return venta;
    }
    @Override
    public List<DetalleVenta> getDetVentas() {
        return List.of();
    }


}
