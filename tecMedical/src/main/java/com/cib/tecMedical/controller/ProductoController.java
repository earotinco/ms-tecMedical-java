package com.cib.tecMedical.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cib.tecMedical.entidades.Producto;
import com.cib.tecMedical.service.ProductoService;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
	@Autowired
	private ProductoService productoService;
	
	 @GetMapping
	 @PreAuthorize("permitAll()")
	    public List<Producto> listarActivos() {
	        return productoService.listarActivos();
	    }
	 
	 
	 @GetMapping("/todos")
	 @PreAuthorize("hasAnyRole('Administrador','Vendedor')")
	    public List<Producto> listarTodos() {
	        return productoService.listarTodos();
	    }
	
	@PostMapping
	@PreAuthorize("hasAnyRole('Administrador','Vendedor')")
    public Producto crearProducto(@RequestBody Producto producto) {
        return productoService.guardar(producto);
    }
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAnyRole('Administrador','Vendedor')")
    public Producto obtenerProducto(@PathVariable Integer id) {
        return productoService.obtenerPorId(id);
    }
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAnyRole('Administrador','Vendedor')")
    public void desactivarProducto(@PathVariable Integer id) {
        productoService.desactivar(id);
    }
}
