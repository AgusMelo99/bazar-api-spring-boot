package com.jmelo.bazar.controller;

import com.jmelo.bazar.model.Producto;
import com.jmelo.bazar.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductoController {
    @Autowired
    private IProductoService prodService;
    //@Autowired
    //private ProductoRepository prodRepo;

    @GetMapping("/bazar/productos")
    public List<Producto> getProductos() {
        return prodService.getProductos();
    }
    @GetMapping("/bazar/productos/{codigo_producto}")
    public Producto traerProducto(@PathVariable Long codigo_producto){
    return prodService.findProducto(codigo_producto);
    }

    @PostMapping("/bazar/productos/crear")
    public String crearProducto(@RequestBody List<Producto> prod) {
        prodService.saveProductos(prod);
        return "Producto agreado correctamente";
    }

    @DeleteMapping("/bazar/productos/delete/{id_producto}")
    public String deleteProducto(@PathVariable Long id_producto) {
        prodService.deleteProducto(id_producto);
        return "Producto borrado exitosamente";
    }

    @PutMapping("bazar/productos/edit")
    public String editProducto(@RequestBody Producto prod) {
        prodService.saveProducto(prod);
        return "Producto actualizado correctamente";
    }

   /* @GetMapping("/bazar/productos/hay_stock")
    public boolean hayStock(@RequestParam Long prodId, int cant) {
        return prodService.hayStock(prodId,cant);
    }*/

    @GetMapping("/bazar/productos/falta_stock")
    public List<Producto> faltaStock() {
        return prodService.getProductosFaltaStock();
    }

    @PutMapping("bazar/productos/cargar_stock/{id_producto}/{cantidad}")
    public String cargarStock(@PathVariable Long id_producto, @PathVariable int cantidad) {
        prodService.actualizarStock(id_producto, cantidad);
        return "Producto actualizado correctamente";
    }

}
