package com.cib.tecMedical.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cib.tecMedical.entidades.Producto;
import com.cib.tecMedical.repository.ProductoRepository;

@Service
public class ProductoService {
	@Autowired
	private ProductoRepository productoRepository;
	
	public List<Producto> listarTodos(){
		return productoRepository.findAll();
	}
	
	public List<Producto> listarActivos(){
		return productoRepository.findByEstadoTrue();
	}
	
	public Producto guardar(Producto producto) {
		return productoRepository.save(producto);
	}
	
	public Producto obtenerPorId(Integer id) {
		return productoRepository.findById(id).orElse(null);
	}
	
	public void desactivar(Integer id) {
		Producto producto = productoRepository.findById(id).orElse(null);
		if(producto != null) {
			producto.setEstado(false);
			productoRepository.save(producto);
		}
	}
}
