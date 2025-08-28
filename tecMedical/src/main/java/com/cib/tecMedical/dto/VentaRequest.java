package com.cib.tecMedical.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VentaRequest {
	
	 private Integer clienteId;
	 private Integer usuarioId;
	    private List<DetalleVentaRequest> detalles;


}
