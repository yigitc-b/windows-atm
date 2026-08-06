package com.web_atm_denemesi.atm_backend.service;

import com.web_atm_denemesi.atm_backend.dto.LoginRequest;
import com.web_atm_denemesi.atm_backend.dto.ServiceResponse;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    public ServiceResponse authenticateUser(LoginRequest loginRequest){
        String dummyUsername = "yigit";
        String dummyPassword = "123";

        if(dummyUsername.equals(loginRequest.getUsername()) && dummyPassword.equals(loginRequest.getPassword())){
            return new ServiceResponse(
                true,
                "Authentication successful",
                java.time.LocalDateTime.now(),
                "200"
            );
        } else {
            return new ServiceResponse(
                false,
                "Invalid username or password",
                java.time.LocalDateTime.now(),
                "401"
            );
        }
    }
}