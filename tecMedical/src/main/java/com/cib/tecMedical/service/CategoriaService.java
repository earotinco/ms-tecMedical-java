package com.cib.tecMedical.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cib.tecMedical.entidades.Categoria;
import com.cib.tecMedical.repository.CategoriaRepository;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public List<Categoria> listarTodos(){
		return categoriaRepository.findAll();
	}
	
	public List<Categoria> listarActivas(){
		return categoriaRepository.findByEstadoTrue();
	}
	
	public Categoria guardar(Categoria categoria) {
		 if(categoria.getNombre() == null || categoria.getNombre().isBlank()) {
		        throw new IllegalArgumentException("El nombre de la categoría no puede estar vacío");
		    }
		return categoriaRepository.save(categoria);
	}
	
	public Categoria obtenerPorId(Integer id) {
		return categoriaRepository.findById(id).
				orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
	}
	
	public void desactivar(Integer id) {
		Categoria categoria= categoriaRepository.findById(id) 
				.orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
		if(categoria != null) {
			categoria.setEstado(false);
			categoriaRepository.save(categoria);
		}
	}
	public void activar(Integer id) {
	    Categoria categoria = obtenerPorId(id);
	    if(categoria != null) {
	        categoria.setEstado(true);
	        guardar(categoria);
	    }
	}

}
