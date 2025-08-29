package com.cib.tecMedical.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cib.tecMedical.dto.DetalleVentaRequest;
import com.cib.tecMedical.dto.DetalleVentaResponse;
import com.cib.tecMedical.entidades.Cliente;
import com.cib.tecMedical.entidades.DetalleVenta;
import com.cib.tecMedical.entidades.Usuario;
import com.cib.tecMedical.entidades.Venta;
import com.cib.tecMedical.repository.ClienteRepository;
import com.cib.tecMedical.repository.ProductoRepository;
import com.cib.tecMedical.repository.UsuarioRepository;
import com.cib.tecMedical.repository.VentaRepository;
import com.cib.tecMedical.dto.VentaRequest;
import com.cib.tecMedical.dto.VentaResponse;
import com.cib.tecMedical.service.VentaService;

@Service
public class VentaServiceImpl implements VentaService {

	private final VentaRepository ventaRepository;

	private final ClienteRepository clienteRepo;

	private final ProductoRepository productoRepo;
	
	private final UsuarioRepository usuarioRepo;

	public VentaServiceImpl(VentaRepository ventaRepository, ClienteRepository clienteRepo,
			ProductoRepository productoRepo, UsuarioRepository usuarioRepo) {
		this.ventaRepository = ventaRepository;
		this.clienteRepo = clienteRepo;
		this.productoRepo = productoRepo;
		this.usuarioRepo = usuarioRepo;
	}

	@Override
	public Venta registrarVentaConDetalles(VentaRequest request) {
		
		Venta venta = new Venta();

		
		Cliente cliente = clienteRepo.findById(request.getClienteId())
				.orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
		venta.setCliente(cliente);

		
		if (request.getUsuarioId() != null) {
            Usuario usuario = usuarioRepo.findById(request.getUsuarioId())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            venta.setUsuario(usuario);
        } else {
            venta.setUsuario(null); 
        }
		
		
		
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
				detalle.setSubtotal(d.getCantidad() * d.getPrecioUnitario());
			}
		}
		venta.setDetalles(detalles);

	
		
		double total = detalles.stream()
			    .mapToDouble(det -> det.getCantidad() * det.getPrecioUnitario())
			    .sum();
			venta.setTotal(total);
			venta.setDetalles(detalles);
			
		

		return ventaRepository.save(venta);
	}

	
	
	@Override
	public List<VentaResponse> listarVentas() {
		 return ventaRepository.findAll()
		            .stream()
		            .map(this::mapToResponse)
		            .toList();
	}

	public VentaResponse mapToResponse(Venta venta) {
	    VentaResponse dto = new VentaResponse();
	    dto.setIdVenta(venta.getIdVenta());
	    dto.setCliente(venta.getCliente().getNombre() + " " + venta.getCliente().getApellido());
	    dto.setVendedor(
	        venta.getUsuario() != null 
	        ? venta.getUsuario().getNombre() + " " + venta.getUsuario().getApellido() 
	        : "Venta Web"
	    );
	    dto.setFecha(venta.getFecha());
	    dto.setTotal(venta.getTotal());

	    List<DetalleVentaResponse> detalles = venta.getDetalles()
	        .stream()
	        .map(det -> new DetalleVentaResponse(
	            det.getIdDetalleVenta(),
	            det.getProducto().getNombre(),
	            det.getCantidad(),
	            det.getPrecioUnitario(),
	            det.getSubtotal()
	        ))
	        .toList();

	    dto.setDetalles(detalles);
	    return dto;
	}


	@Override
	public VentaResponse buscarPorId(Integer id) {
	    Venta venta = ventaRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Venta no encontrada"));

	    VentaResponse dto = new VentaResponse();
	    dto.setIdVenta(venta.getIdVenta());
	    dto.setCliente(venta.getCliente().getNombre() + " " + venta.getCliente().getApellido());
	    dto.setVendedor(
	    	    venta.getUsuario() != null
	    	        ? venta.getUsuario().getNombre() + " " + venta.getUsuario().getApellido()
	    	        : "No asignado"
	    	);
	    dto.setFecha(venta.getFecha());
	    dto.setTotal(venta.getTotal());

	    List<DetalleVentaResponse> detalles = venta.getDetalles().stream()
	            .map(det -> new DetalleVentaResponse(
	                    det.getIdDetalleVenta(),
	                    det.getProducto().getNombre(),
	                    det.getCantidad(),
	                    det.getPrecioUnitario(),
	                    det.getSubtotal()
	            ))
	            .toList();

	    dto.setDetalles(detalles);

	    return dto;
	}

	
	@Override
	public List<Venta> listarVentasWeb() {
	    return ventaRepository.listarVentasWeb();
	}

	

}
