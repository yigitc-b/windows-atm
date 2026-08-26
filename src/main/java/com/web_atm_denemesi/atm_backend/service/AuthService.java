package com.web_atm_denemesi.atm_backend.service;

import com.web_atm_denemesi.atm_backend.dto.LoginRequest;
import com.web_atm_denemesi.atm_backend.dto.AuthServiceResponse;
import com.web_atm_denemesi.atm_backend.dto.User;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    public AuthServiceResponse authenticateUser(LoginRequest loginRequest){

        JWTService jwtService=new JWTService();
        DatabaseService databaseService=new DatabaseService();
        User user=databaseService.getUser(loginRequest.getUsername());

        if(user.getUserName().equals(loginRequest.getUsername()) && user.getPassword().equals(loginRequest.getPassword())){
            return new AuthServiceResponse(
                true,
                "Authentication successful",
                java.time.LocalDateTime.now(),
                "200",
                jwtService.generateToken(user.getUserName())
                
            );
        } else {
            return new AuthServiceResponse(
                false,
                "Invalid username or password",
                java.time.LocalDateTime.now(),
                "401",
                null
            );
        }
    }
}