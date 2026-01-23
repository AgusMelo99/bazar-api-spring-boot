package com.jmelo.bazar.repository;

import com.jmelo.bazar.model.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaDetalleRepository extends JpaRepository<DetalleVenta,Long> {
}
