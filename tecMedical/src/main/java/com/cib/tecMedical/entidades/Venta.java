package com.cib.tecMedical.entidades;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "venta")
@Data
public class Venta {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id_venta")
	    private Integer idVenta;

	    @ManyToOne
	    @JoinColumn(name = "id_cliente", nullable = false) 
	    private Cliente cliente;

	    @ManyToOne
	    @JoinColumn(name = "id_usuario", nullable = true)
	    private Usuario usuario;

	    @Column(nullable = false)
	    private LocalDateTime fecha = LocalDateTime.now();

	    @Column(nullable = false)
	    private double total;

	    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	    @JsonIgnore
	    private List<DetalleVenta> detalles = new ArrayList<>();

}
