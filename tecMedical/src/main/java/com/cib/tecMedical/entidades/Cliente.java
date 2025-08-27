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
@Table(name = "cliente") 
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Integer idCliente;

    @Column(name = "nombre", nullable = false, length = 50) 
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 50) 
    private String apellido;

    @Column(name = "dni", unique = true, length = 20)
    private String dni;

    @Column(name = "correo", unique = true, length = 100)
    private String correo;

    @Column(name = "telefono", length = 20) 
    private String telefono;

    @Column(name = "direccion", length = 255)
    private String direccion;
}
