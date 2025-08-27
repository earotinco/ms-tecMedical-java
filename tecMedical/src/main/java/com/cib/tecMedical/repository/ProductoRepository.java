package com.cib.tecMedical.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cib.tecMedical.entidades.Producto;

public interface ProductoRepository extends JpaRepository<Producto,Integer>{
	List<Producto> findByEstadoTrue();
	List<Producto> findByDescripcionContainingIgnoreCase(String descripcion);
}
