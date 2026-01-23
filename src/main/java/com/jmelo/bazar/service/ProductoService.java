package com.jmelo.bazar.service;

import com.jmelo.bazar.model.Producto;
import com.jmelo.bazar.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductoService implements IProductoService {

    @Autowired
    private ProductoRepository prodRepo;

    @Override
    public List<Producto> getProductos() {
        return prodRepo.findAll();
    }

    @Override
    public void saveProductos(List<Producto> prod) {
        prodRepo.saveAll(prod);
    }

    @Override
    public void saveProducto(Producto prod) {
        prodRepo.save(prod);
    }

    @Override
    public void deleteProducto(Long id) {
        prodRepo.deleteById(id);
    }

    @Override
    public Producto findProducto(Long id) {
        return prodRepo.findById(id).orElseThrow(() ->new RuntimeException("Producto inexsistente!"));
    }

    @Override
    public List<Producto> getProductosFaltaStock() {
        return prodRepo.findByCantidadDisponibleLessThan(5.0);
    }
    @Override
    public boolean hayStock(Long prodId, double cant) {
        return prodRepo.findById(prodId)
                .map(prod ->prod.getCantidadDisponible()>= cant)
                .orElse(false);
    }

    @Override
    @Transactional
    public void actualizarStock(Long prodId, int cant) {
        Producto prod = prodRepo.findById(prodId).orElseThrow(()->new RuntimeException("Producto inexistente"));
            prod.setCantidadDisponible(prod.getCantidadDisponible() + cant);
            prodRepo.save(prod);
    }


}
