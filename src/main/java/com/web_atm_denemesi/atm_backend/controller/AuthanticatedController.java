package com.web_atm_denemesi.atm_backend.controller;

import com.web_atm_denemesi.atm_backend.dto.ApiResponse;
import com.web_atm_denemesi.atm_backend.service.DatabaseService;

import java.math.BigDecimal;

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

    @GetMapping("/account/balance")
    public ResponseEntity<ApiResponse<BigDecimal>> getBalance (Authentication authentication){
        return ResponseEntity.ok(ApiResponse.success(databaseService.getUserDetailsForServer(authentication.getName()).getBalance(), "Balance başarı ile getirildi"));
    }
}