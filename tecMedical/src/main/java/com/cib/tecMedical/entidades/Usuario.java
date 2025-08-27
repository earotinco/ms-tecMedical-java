package com.cib.tecMedical.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdUsuario")
    private Long idUsuario;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Apellido")
    private String apellido;

    @Column(name = "Correo")
    private String correo;

    @Column(name = "Usuario")
    private String usuario;

    @Column(name = "Password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "IdRol")
    private Rol rol;

    @Column(name = "Estado")
    private Boolean estado = true;
}
