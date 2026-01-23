package com.jmelo.bazar.repository;

import com.jmelo.bazar.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Long> {
    List<Producto> findByCantidadDisponibleLessThan(double cant);
}
