package com.cib.tecMedical.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetalleVentaRequest {
	
	 private Integer productoId;
	    private Integer cantidad;
	    private Double precioUnitario;

}
