package com.cib.tecMedical.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cib.tecMedical.entidades.Categoria;
import com.cib.tecMedical.service.CategoriaService;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {
	@Autowired
	private CategoriaService categoriaService;
	
	
	
	@GetMapping("/todos")
	@PreAuthorize("hasAnyRole('Administrador','Vendedor')")
	public List<Categoria> listarTodasCategorias() {
	    return categoriaService.listarTodos();
	}
	
	@GetMapping
	@PreAuthorize("permitAll()")
	public List<Categoria> listarCategoriasActivas() {
	    return categoriaService.listarActivas();
	}
	
	@PostMapping
	@PreAuthorize("hasAnyRole('Administrador','Vendedor')")
	public Categoria crearCategoria(@RequestBody Categoria categoria) {
	    return categoriaService.guardar(categoria);
	}

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('Administrador','Vendedor')")
    public Categoria obtenerCategoria(@PathVariable Integer id) {
        return categoriaService.obtenerPorId(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('Administrador','Vendedor')")
    public void desactivarCategoria(@PathVariable Integer id) {
        categoriaService.desactivar(id);
    }
    @PutMapping("/{id}/activar")
    @PreAuthorize("hasAnyRole('Administrador','Vendedor')")
    public void activarCategoria(@PathVariable Integer id) {
        categoriaService.activar(id);
    }
}
