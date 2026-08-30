package com.web_atm_denemesi.atm_backend.dto;

import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class AuthServiceResponse{

    private String token;
    private String tokenType;
    private String expiresIn;
    private String username;
    private String email;
    private List<String> roles;
}