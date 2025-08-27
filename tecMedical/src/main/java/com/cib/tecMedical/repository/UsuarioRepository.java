package com.cib.tecMedical.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cib.tecMedical.entidades.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	Usuario findByUsuario(String getusername);

}
