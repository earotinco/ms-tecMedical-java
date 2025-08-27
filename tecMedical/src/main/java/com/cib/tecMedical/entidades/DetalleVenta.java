package com.cib.tecMedical.entidades;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name = "DetalleVenta")
@Data
public class DetalleVenta {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer idDetalleVenta;

	    @ManyToOne
	    @JoinColumn(name = "IdVenta", nullable = false)
	    private Venta venta;

	    @ManyToOne
	    @JoinColumn(name = "IdProducto", nullable = false)
	    private Producto producto;

	    @Column(nullable = false)
	    private int cantidad;

	    @Column(nullable = false)
	    private double precioUnitario;

	    @Column(nullable = false)
	    private double subtotal;

	    @PrePersist
	    @PreUpdate
	    public void calcularSubtotal() {
	        this.subtotal = this.cantidad * this.precioUnitario;
	    }

}
