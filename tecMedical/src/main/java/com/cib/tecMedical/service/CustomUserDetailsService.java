package com.cib.tecMedical.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.cib.tecMedical.entidades.Usuario;
import com.cib.tecMedical.repository.UsuarioRepository;


@Service
public class CustomUserDetailsService  implements UserDetailsService{

	
	private final UsuarioRepository usuarioRepo;

    public CustomUserDetailsService(UsuarioRepository usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = usuarioRepo.findByUsuario(username);
        if (user == null) throw new UsernameNotFoundException("Usuario no encontrado");

        return User.builder()
                .username(user.getUsuario())
                .password(user.getPassword())
                .roles(user.getRol().getNombre()) 
                .build();
    }
}
