package com.web_atm_denemesi.atm_backend.controller;

import com.web_atm_denemesi.atm_backend.dto.LoginRequest;
import com.web_atm_denemesi.atm_backend.dto.UserDetailsForClient;
import com.web_atm_denemesi.atm_backend.dto.AuthServiceResponse;
import com.web_atm_denemesi.atm_backend.dto.DatabaseServiceResponse;
import com.web_atm_denemesi.atm_backend.service.AuthService;
import com.web_atm_denemesi.atm_backend.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.security.autoconfigure.SecurityProperties.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class AuthanticatedController{

    private final DatabaseService databaseService;

    @Autowired
    public AuthanticatedController(DatabaseService databaseService) {
        this.databaseService=databaseService;
    }

    @GetMapping("/user-details")
    public ResponseEntity<UserDetailsForClient> getUserDetails (){

        UserDetailsForClient response = databaseService.getUserDetailsForClient("yigit").getUser();
        return ResponseEntity.ok(response);
    }
}