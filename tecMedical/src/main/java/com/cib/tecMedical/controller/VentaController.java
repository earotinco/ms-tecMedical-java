package com.cib.tecMedical.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
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

	private final VentaService ventaService;

    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    
    
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
    public List<Venta> listarVentas() {
        return ventaService.listarVentas();
    }

    @GetMapping("/{id}")
    public VentaResponse buscarPorId(@PathVariable Integer id) {
        return ventaService.buscarPorId(id);
    }
}
