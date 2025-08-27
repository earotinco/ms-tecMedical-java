package com.cib.tecMedical.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cib.tecMedical.entidades.Cliente;
import com.cib.tecMedical.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepo;

    // Listar todos los clientes
    public List<Cliente> listarTodos() {
		// TODO Auto-generated method stub
		return clienteRepo.findAll();
	}

    // Buscar cliente por ID
    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepo.findById(id);
    }

    // Crear nuevo cliente
    public Cliente crear(Cliente cliente) {
        return clienteRepo.save(cliente);
    }

    // Actualizar cliente existente
    public Cliente actualizar(Long id, Cliente nuevo) {
        Cliente existente = clienteRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));

        existente.setNombre(nuevo.getNombre());
        existente.setApellido(nuevo.getApellido());
        existente.setDni(nuevo.getDni());
        existente.setCorreo(nuevo.getCorreo());
        existente.setTelefono(nuevo.getTelefono());
        existente.setDireccion(nuevo.getDireccion());

        return clienteRepo.save(existente);
    }

    // Eliminar cliente por ID
    public void eliminar(Long id) {
        if (!clienteRepo.existsById(id)) {
            throw new RuntimeException("No se puede eliminar. Cliente no encontrado con ID: " + id);
        }
        clienteRepo.deleteById(id);
    }

	
}