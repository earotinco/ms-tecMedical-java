package com.cib.tecMedical.entidades;

import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Categoria {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long idCategoria;
	 
	 @Column(nullable = false)
	 private String nombre;
	 
	 private String descripcion;
	 
	 private Boolean estado = true;
	 
	 @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	 private List<Producto> productos;
}