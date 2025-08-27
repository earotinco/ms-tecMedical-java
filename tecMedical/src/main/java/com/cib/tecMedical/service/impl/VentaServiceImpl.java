package com.cib.tecMedical.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cib.tecMedical.entidades.Venta;
import com.cib.tecMedical.repository.VentaRepository;
import com.cib.tecMedical.service.VentaService;

@Service
public class VentaServiceImpl implements VentaService{

	private final VentaRepository ventaRepository;

    public VentaServiceImpl(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    @Override
    public Venta registrarVenta(Venta venta) {
        
    	// Calculo del total de la venta - subtotales
        double total = venta.getDetalles()
                .stream()
                .mapToDouble(d -> d.getCantidad() * d.getPrecioUnitario())
                .sum();
        venta.setTotal(total);

        
        venta.getDetalles().forEach(d -> d.setVenta(venta));

        return ventaRepository.save(venta);
    }

    @Override
    public List<Venta> listarVentas() {
        return ventaRepository.findAll();
    }

    @Override
    public Venta buscarPorId(Integer id) {
        return ventaRepository.findById(id).orElse(null);
    }
}
