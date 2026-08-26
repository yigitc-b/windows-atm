package com.web_atm_denemesi.atm_backend.controller;

import com.web_atm_denemesi.atm_backend.dto.LoginRequest;
import com.web_atm_denemesi.atm_backend.dto.User;
import com.web_atm_denemesi.atm_backend.dto.AuthServiceResponse;
import com.web_atm_denemesi.atm_backend.service.AuthService;
import com.web_atm_denemesi.atm_backend.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class UserController{

    private final DatabaseService databaseService;

    @Autowired
    public UserController(DatabaseService databaseService) {
        this.databaseService=databaseService;
    }

    @GetMapping("/user-details")
    public ResponseEntity<User> getUserDetails (){

        return ResponseEntity.ok(databaseService.getUser("yigit"));
    }
}