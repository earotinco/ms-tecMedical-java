package com.cib.tecMedical.service;

import java.util.List;

import com.cib.tecMedical.dto.VentaRequest;
import com.cib.tecMedical.entidades.Venta;

public interface VentaService {
	
	    Venta registrarVentaConDetalles(VentaRequest request);
	    List<Venta> listarVentas();
	    Venta buscarPorId(Integer id);

}
