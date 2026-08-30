package com.web_atm_denemesi.atm_backend.service;

import com.web_atm_denemesi.atm_backend.dto.LoginRequest;
import com.web_atm_denemesi.atm_backend.dto.AuthServiceResponse;
import com.web_atm_denemesi.atm_backend.dto.DatabaseServiceResponse;
import com.web_atm_denemesi.atm_backend.dto.UserDetailsForServer;
import com.web_atm_denemesi.atm_backend.exception.InvalidCredentialsException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

//LOGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGING
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//LOGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGING

@Service
public class AuthService {
    //LOGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGING
    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);
    //LOGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGING

    private final JwtService jwtService;
    private final DatabaseService databaseService;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthService(JwtService jwtService, DatabaseService databaseService ,BCryptPasswordEncoder passwordEncoder){
        this.jwtService = jwtService;
        this.databaseService = databaseService;
        this.passwordEncoder = passwordEncoder;
    }
    public AuthServiceResponse authenticateUser(LoginRequest loginRequest){
        
        UserDetailsForServer user = databaseService.getUserDetailsForServer(loginRequest.getUsername());

        if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
            logger.warn("Hatalı şifre denemesi yapıldı. Kullanıcı adı: {}",loginRequest.getUsername());
            throw new InvalidCredentialsException("Kullanıcı adı veya şifre hatalı");
        }

        logger.info("Kullanıcı doğrulama başaralı. Token üretiliyor: {}", loginRequest.getUsername());
        return new AuthServiceResponse(
            jwtService.generateToken(user.getUsername()),
            "Bearer",
            "10 MINS",
            user.getUsername(),
            "yigit@gmail.com",
            List.of("ROLE_USER", "ROLE_ADMIN")
        );
    }
}