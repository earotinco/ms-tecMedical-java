package com.cib.tecMedical.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cib.tecMedical.entidades.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Usuario findByUsuario(String getusername);

}
