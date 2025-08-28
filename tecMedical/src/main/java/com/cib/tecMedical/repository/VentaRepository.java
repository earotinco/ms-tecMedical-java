package com.cib.tecMedical.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cib.tecMedical.entidades.Venta;

public interface VentaRepository extends JpaRepository<Venta, Integer>{
	
	@Query("SELECT v FROM Venta v WHERE v.usuario IS NULL")
	List<Venta> listarVentasWeb();
}
