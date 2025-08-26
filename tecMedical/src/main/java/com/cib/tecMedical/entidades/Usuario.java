package com.cib.tecMedical.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdUsuario")
    private Long idUsuario;

    @Column(name = "Nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "Apellido", nullable = false, length = 50)
    private String apellido;

    @Column(name = "Correo", nullable = false, unique = true, length = 100)
    private String correo;

    @Column(name = "Usuario", nullable = false, unique = true, length = 50)
    private String usuario;

    @Column(name = "Password", nullable = false, length = 255)
    private String password;

    @ManyToOne
    @JoinColumn(name = "IdRol", nullable = false)
    private Rol rol;

    @Column(name = "Estado")
    private Boolean estado = true;
}
