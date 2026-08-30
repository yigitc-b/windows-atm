package com.web_atm_denemesi.atm_backend.controller;

import com.web_atm_denemesi.atm_backend.dto.UserDetailsForClient;
import com.web_atm_denemesi.atm_backend.service.DatabaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class AuthanticatedController{

    @Autowired
    private final DatabaseService databaseService;

    public AuthanticatedController(DatabaseService databaseService) {
        this.databaseService=databaseService;
    }

    @GetMapping("/user-details")
    public ResponseEntity<UserDetailsForClient> getUserDetails (Authentication authentication){

        UserDetailsForClient response = databaseService.getUserDetailsForClient(authentication.getName()).getUser();
        return ResponseEntity.ok(response);
    }
}