package com.cib.tecMedical.test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestByCrypt {

	public static void main(String[] args) {
		  BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	        String passwordPlano = "123456";
	        String hashBD = "$2a$10$1.S0/lNatfhqnWBEve6oYOJcRGwK/LJ3XZNPcOvdeiwS06R7Jn2oa";

	        boolean coincide = encoder.matches(passwordPlano, hashBD);
	        System.out.println("¿Coincide la contraseña? " + coincide);
	    }

}
