package com.cib.tecMedical.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.cib.tecMedical.dto.LoginRequest;
import com.cib.tecMedical.entidades.Usuario;
import com.cib.tecMedical.repository.UsuarioRepository;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200") // Ajusta según tu frontend
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private PasswordEncoder encoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        // Buscar usuario por nombre de usuario
        Usuario user = usuarioRepo.findByUsuario(request.getUsername());

        // Validar existencia y contraseña
        if (user != null && encoder.matches(request.getPassword(), user.getPassword())) {
            String mensaje = "Login exitoso como " + user.getRol().getNombre();
            return ResponseEntity.ok(mensaje);
        }

        // Si no coincide, devolver error 401
        return ResponseEntity.status(401).body("Credenciales inválidas");
    }
}
