package com.cib.tecMedical.test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GenerarHash {
	
	 public static void main(String[] args) {
	        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	        String passwordPlano = "123456";

	        String hashNuevo = encoder.encode(passwordPlano);
	        System.out.println("Hash generado: " + hashNuevo);
	    }

}
