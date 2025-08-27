package com.cib.tecMedical.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cib.tecMedical.entidades.Producto;
import com.cib.tecMedical.service.ProductoService;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
	@Autowired
	private ProductoService productoService;
	
	@GetMapping
	public List<Producto> listarProductos(@RequestParam(required = false) Boolean todos){
		if(Boolean.TRUE.equals(todos)) {
		return productoService.listarTodos();
		}
		return productoService.listarActivos();
	}
	
	@PostMapping
    public Producto crearProducto(@RequestBody Producto producto) {
        return productoService.guardar(producto);
    }
	
	@GetMapping("/{id}")
    public Producto obtenerProducto(@PathVariable Long id) {
        return productoService.obtenerPorId(id);
    }
	
	@DeleteMapping("/{id}")
    public void desactivarProducto(@PathVariable Long id) {
        productoService.desactivar(id);
    }
}
