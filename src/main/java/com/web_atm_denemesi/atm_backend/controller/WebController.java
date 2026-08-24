package com.web_atm_denemesi.atm_backend.controller;

import com.web_atm_denemesi.atm_backend.dto.LoginRequest;
import com.web_atm_denemesi.atm_backend.dto.LoginServiceResponse;
import com.web_atm_denemesi.atm_backend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class WebController {

    private final AuthService authService;
    @Value("${info.app.version}")
    private String version;

    // Dependency Injection (Spring'in AuthService'i otomatik bağlaması)
    @Autowired
    public WebController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login.html";
    }

    @GetMapping("/about")
    public String getAboutPage() {
        return "about.html";
    }

    @GetMapping("/dashboard")
    public String getDashboardPage() {
        return "dashboard.html";
    }

    @GetMapping("/api/version")
    @ResponseBody
    public String getVersion() {
        return "Application Version: " + version;
    }
    

    @PostMapping("/api/login")
    @ResponseBody
    public ResponseEntity<LoginServiceResponse> handleLogin(@RequestBody LoginRequest loginRequest) {
        
        // İş mantığını Service katmanına devrediyoruz
        LoginServiceResponse response = authService.authenticateUser(loginRequest);

        // Eğer giriş başarısızsa HTTP 401 (Unauthorized), başarılıysa HTTP 200 (OK) dönüyoruz
        if (!response.isSuccess()) {
            return ResponseEntity.status(401).body(response);
        }

        return ResponseEntity.ok(response);
    }
}