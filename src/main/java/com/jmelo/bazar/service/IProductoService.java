package com.jmelo.bazar.service;

import com.jmelo.bazar.model.Producto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IProductoService {

    List<Producto> getProductos();

    void saveProductos(List<Producto> prod);

    void saveProducto(Producto prod);

    void deleteProducto(Long id);

    Producto findProducto(Long id);

    List<Producto> getProductosFaltaStock();

    boolean hayStock(Long prodId, double cant);

    void actualizarStock(Long prodId, int cant);

}
