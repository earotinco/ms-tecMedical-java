package com.cib.tecMedical.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cib.tecMedical.entidades.Categoria;
import com.cib.tecMedical.entidades.Producto;
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
		return categoriaRepository.save(categoria);
	}
	
	public Categoria obtenerPorId(Integer id) {
		return categoriaRepository.findById(id).orElse(null);
	}
	
	public void desactivar(Integer id) {
		Categoria categoria= categoriaRepository.findById(id).orElse(null);
		if(categoria != null) {
			categoria.setEstado(false);
			categoriaRepository.save(categoria);
		}
	}
}
