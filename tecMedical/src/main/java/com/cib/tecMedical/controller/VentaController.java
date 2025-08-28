package com.cib.tecMedical.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cib.tecMedical.dto.VentaRequest;
import com.cib.tecMedical.dto.VentaResponse;
import com.cib.tecMedical.entidades.Venta;
import com.cib.tecMedical.service.VentaService;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

	@Autowired
	private  VentaService ventaService;

    

    
    
    @PostMapping
    public ResponseEntity<?> registrarVenta(@RequestBody VentaRequest request) {
        try {
            Venta ventaGuardada = ventaService.registrarVentaConDetalles(request);
            return ResponseEntity.ok(ventaGuardada);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }



    @GetMapping
    @PreAuthorize("hasAnyRole('Administrador','Vendedor')")
    public List<VentaResponse> listarVentas() {
        return ventaService.listarVentas();
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('Administrador','Vendedor')")
    public VentaResponse buscarPorId(@PathVariable Integer id) {
        return ventaService.buscarPorId(id);
    }
}
