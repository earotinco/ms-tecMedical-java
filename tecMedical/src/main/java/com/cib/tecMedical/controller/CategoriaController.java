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

import com.cib.tecMedical.entidades.Categoria;
import com.cib.tecMedical.service.CategoriaService;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {
	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping
	public List<Categoria> listarCategorias(@RequestParam(required = false) Boolean todos){
		if(Boolean.TRUE.equals(todos)) {
			return categoriaService.listarTodos();
		}
		return categoriaService.listarActivas();
	}
	
	@PostMapping
    public Categoria crearCategoria(@RequestBody Categoria categoria) {
        return categoriaService.guardar(categoria);
    }

    @GetMapping("/{id}")
    public Categoria obtenerCategoria(@PathVariable Integer id) {
        return categoriaService.obtenerPorId(id);
    }

    @DeleteMapping("/{id}")
    public void desactivarCategoria(@PathVariable Integer id) {
        categoriaService.desactivar(id);
    }
}
