package com.cib.tecMedical.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cib.tecMedical.dto.DetalleVentaRequest;
import com.cib.tecMedical.entidades.Cliente;
import com.cib.tecMedical.entidades.DetalleVenta;
import com.cib.tecMedical.entidades.Venta;
import com.cib.tecMedical.repository.ClienteRepository;
import com.cib.tecMedical.repository.ProductoRepository;
import com.cib.tecMedical.repository.VentaRepository;
import com.cib.tecMedical.dto.VentaRequest;
import com.cib.tecMedical.service.VentaService;

@Service
public class VentaServiceImpl implements VentaService {

	private final VentaRepository ventaRepository;

	private final ClienteRepository clienteRepo;

	private final ProductoRepository productoRepo;

	public VentaServiceImpl(VentaRepository ventaRepository, ClienteRepository clienteRepo,
			ProductoRepository productoRepo) {
		this.ventaRepository = ventaRepository;
		this.clienteRepo = clienteRepo;
		this.productoRepo = productoRepo;
	}

	@Override
	public Venta registrarVentaConDetalles(VentaRequest request) {
		
		Venta venta = new Venta();
		venta.setFecha(request.getFecha());

		
		Cliente cliente = clienteRepo.findById(request.getClienteId())
				.orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
		venta.setCliente(cliente);

		
		List<DetalleVenta> detalles = new ArrayList<>();
		if (request.getDetalles() != null) {
			for (DetalleVentaRequest d : request.getDetalles()) {
				DetalleVenta detalle = new DetalleVenta();
				detalle.setProducto(productoRepo.findById(d.getProductoId())
						.orElseThrow(() -> new RuntimeException("Producto no encontrado")));
				detalle.setCantidad(d.getCantidad());
				detalle.setPrecioUnitario(d.getPrecioUnitario());
				detalle.setVenta(venta);
				detalles.add(detalle);
			}
		}
		venta.setDetalles(detalles);

		double total = venta.getDetalles().stream().mapToDouble(det -> det.getCantidad() * det.getPrecioUnitario())
				.sum();
		venta.setTotal(total);

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
