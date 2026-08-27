package com.web_atm_denemesi.atm_backend.controller;

import com.web_atm_denemesi.atm_backend.dto.UserDetailsForClient;
import com.web_atm_denemesi.atm_backend.service.DatabaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class AuthanticatedController{

    private final DatabaseService databaseService;

    public AuthanticatedController(DatabaseService databaseService) {
        this.databaseService=databaseService;
    }

    @GetMapping("/user-details")
    public ResponseEntity<UserDetailsForClient> getUserDetails (){

        UserDetailsForClient response = databaseService.getUserDetailsForClient("yigit").getUser();
        return ResponseEntity.ok(response);
    }
}