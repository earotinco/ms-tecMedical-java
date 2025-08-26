package com.cib.tecMedical.entidades;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="Producto")
@Data
public class Producto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProducto;
	
	@Column(nullable = false)
	private String nombre;
	
	private String descripcion;
	
	@Column(nullable = false)
	private double precio;
	
	@Column(nullable = false)
	private Integer Stock = 0;
	
	private Boolean estado = true;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdCategoria", nullable = false)
    private Categoria categoria;
}
