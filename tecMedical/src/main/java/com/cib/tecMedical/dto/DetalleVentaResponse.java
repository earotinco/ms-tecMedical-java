package com.cib.tecMedical.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleVentaResponse {
	
	private Integer idDetalle;
    private String producto;
    private Integer cantidad;
    private Double precioUnitario;
    private Double subtotal;

}
