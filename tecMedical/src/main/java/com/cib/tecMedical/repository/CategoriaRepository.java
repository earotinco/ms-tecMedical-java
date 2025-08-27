package com.cib.tecMedical.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cib.tecMedical.entidades.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria,Long>{
	List<Categoria> findByEstadoTrue();
}
