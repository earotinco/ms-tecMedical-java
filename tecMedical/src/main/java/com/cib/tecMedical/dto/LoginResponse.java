package com.cib.tecMedical.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponse {
	
	

private String mensaje;
    private String rol;
    private Integer usuarioId;


}
