package com.web_atm_denemesi.atm_backend.controller;

import com.web_atm_denemesi.atm_backend.dto.LoginRequest;
import com.web_atm_denemesi.atm_backend.dto.ServiceResponse;
import com.web_atm_denemesi.atm_backend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    private final AuthService authService;

    // Dependency Injection (Spring'in AuthService'i otomatik bağlaması)
    @Autowired
    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "forward:/login.html";
    }

    @PostMapping("/api/login")
    @ResponseBody
    public ResponseEntity<ServiceResponse> handleLogin(@RequestBody LoginRequest loginRequest) {
        
        // İş mantığını Service katmanına devrediyoruz
        ServiceResponse response = authService.authenticateUser(loginRequest);

        // Eğer giriş başarısızsa HTTP 401 (Unauthorized), başarılıysa HTTP 200 (OK) dönüyoruz
        if (!response.isSuccess()) {
            return ResponseEntity.status(401).body(response);
        }

        return ResponseEntity.ok(response);
    }
}