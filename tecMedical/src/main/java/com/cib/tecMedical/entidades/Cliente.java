package com.cib.tecMedical.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "clientes")
public class Cliente {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "IdCliente")
	    private Long idCliente;

	    @Column(name = "Nombre", nullable = false, length = 50)
	    private String nombre;

	    @Column(name = "Apellido", nullable = false, length = 50)
	    private String apellido;

	    @Column(name = "DNI", unique = true, length = 20)
	    private String dni;

	    @Column(name = "Correo", unique = true, length = 100)
	    private String correo;

	    @Column(name = "Telefono", length = 20)
	    private String telefono;

	    @Column(name = "Direccion", length = 255)
	    private String direccion;
    
}