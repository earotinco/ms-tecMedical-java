package com.cib.tecMedical.service;

import java.util.List;

import com.cib.tecMedical.dto.VentaRequest;
import com.cib.tecMedical.dto.VentaResponse;
import com.cib.tecMedical.entidades.Venta;

public interface VentaService {
	
	    Venta registrarVentaConDetalles(VentaRequest request);
	    List<VentaResponse> listarVentas();
	    VentaResponse buscarPorId(Integer id);
	    List<Venta> listarVentasWeb() ;


}
