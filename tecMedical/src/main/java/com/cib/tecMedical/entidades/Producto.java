package com.cib.tecMedical.entidades;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "producto") 
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto") 
    private Integer idProducto;

    @Column(name = "nombre", nullable = false) 
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "precio", nullable = false)
    private double precio;

    @Column(name = "stock", nullable = false) 
    private Integer stock = 0;

    @Column(name = "estado") // 
    private Boolean estado = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria", nullable = false) 
    private Categoria categoria;
}
