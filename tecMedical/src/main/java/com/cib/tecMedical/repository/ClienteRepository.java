package com.cib.tecMedical.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cib.tecMedical.entidades.Cliente;
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
