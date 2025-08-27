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
@Table(name = "usuario") 
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario") 
    private Integer idUsuario;

    @Column(name = "nombre") 
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "correo") 
    private String correo;

    @Column(name = "usuario") 
    private String usuario;

    @Column(name = "password") 
    private String password;

    @ManyToOne
    @JoinColumn(name = "id_rol") 
    private Rol rol;

    @Column(name = "estado") 
    private Boolean estado = true;
}
