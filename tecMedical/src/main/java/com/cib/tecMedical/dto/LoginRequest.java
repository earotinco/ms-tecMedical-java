package com.cib.tecMedical.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    private String username;
    private String password;

    public LoginRequest() {}
}
