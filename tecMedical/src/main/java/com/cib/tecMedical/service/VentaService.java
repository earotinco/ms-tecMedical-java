package com.cib.tecMedical.service;

import java.util.List;

import com.cib.tecMedical.entidades.Venta;

public interface VentaService {
	
	    Venta registrarVenta(Venta venta);
	    List<Venta> listarVentas();
	    Venta buscarPorId(Integer id);

}
