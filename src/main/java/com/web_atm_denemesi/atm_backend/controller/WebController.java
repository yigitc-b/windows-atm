package com.web_atm_denemesi.atm_backend.controller;

import com.web_atm_denemesi.atm_backend.dto.LoginRequest;
import com.web_atm_denemesi.atm_backend.dto.ApiResponse;
import com.web_atm_denemesi.atm_backend.dto.AuthServiceResponse;
import com.web_atm_denemesi.atm_backend.service.AuthService;

import lombok.RequiredArgsConstructor;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/no-auth")
@RequiredArgsConstructor
public class WebController {

    @Autowired
    private final AuthService authService;
    @Value("${info.app.version}")
    private String version;

    @GetMapping("/version")
    @ResponseBody
    public String getVersion() {
        return "Application Version: " + version;
    }
    

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<ApiResponse<AuthServiceResponse>> getJwtToken(@RequestBody LoginRequest loginRequest) {

        AuthServiceResponse response = authService.authenticateUser(loginRequest);

        return ResponseEntity.ok(ApiResponse.success(response, "Oturum açma başarılı"));
    }
}