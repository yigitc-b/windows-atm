package com.web_atm_denemesi.atm_backend.controller;

import com.web_atm_denemesi.atm_backend.dto.LoginRequest;
import com.web_atm_denemesi.atm_backend.dto.AuthServiceResponse;
import com.web_atm_denemesi.atm_backend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/no-auth")
public class WebController {

    private final AuthService authService;
    @Value("${info.app.version}")
    private String version;

    // Dependency Injection (Spring'in AuthService'i otomatik bağlaması)
    @Autowired
    public WebController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/version")
    @ResponseBody
    public String getVersion() {
        return "Application Version: " + version;
    }
    

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<AuthServiceResponse> handleLogin(@RequestBody LoginRequest loginRequest) {
        
        // İş mantığını Service katmanına devrediyoruz
        AuthServiceResponse response = authService.authenticateUser(loginRequest);

        // Eğer giriş başarısızsa HTTP 401 (Unauthorized), başarılıysa HTTP 200 (OK) dönüyoruz
        if (!response.isSuccess()) {
            return ResponseEntity.status(response.getStatusCode()).body(response);
        }

        return ResponseEntity.ok(response);
    }
}