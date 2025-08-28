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
	    if(producto.getNombre() == null || producto.getNombre().isBlank()) {
	        throw new IllegalArgumentException("El nombre del producto no puede estar vacío");
	    }
	    if(producto.getPrecio() < 0) {
	        throw new IllegalArgumentException("El precio no puede ser negativo");
	    }
	    if(producto.getStock() < 0) {
	        throw new IllegalArgumentException("El stock no puede ser negativo");
	    }
	    return productoRepository.save(producto);
	}

	
	public Producto obtenerPorId(Integer id) {
		return productoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Producto no encontrado"));
	}
	
	public void desactivar(Integer id) {
	    Producto producto = productoRepository.findById(id)
	        .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
	    producto.setEstado(false);
	    productoRepository.save(producto);
	}

}
