package com.cib.tecMedical.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VentaRequest {
	
	 private Integer clienteId;
	    private LocalDateTime fecha;
	    private List<DetalleVentaRequest> detalles;


}
