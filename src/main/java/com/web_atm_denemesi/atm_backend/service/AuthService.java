package com.web_atm_denemesi.atm_backend.service;

import com.web_atm_denemesi.atm_backend.dto.LoginRequest;
import com.web_atm_denemesi.atm_backend.dto.AuthServiceResponse;
import com.web_atm_denemesi.atm_backend.dto.DatabaseServiceResponse;
import com.web_atm_denemesi.atm_backend.dto.UserDetailsForServer;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
//LOGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGING
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//LOGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGING

@Service
public class AuthService {
    //LOGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGING
    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);
    //LOGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGING

    private final JWTService jwtService;
    private final DatabaseService databaseService;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthService(JWTService jwtService, DatabaseService databaseService ,BCryptPasswordEncoder passwordEncoder){
        this.jwtService = jwtService;
        this.databaseService = databaseService;
        this.passwordEncoder = passwordEncoder;
    }
    public AuthServiceResponse authenticateUser(LoginRequest loginRequest){

        try {

            DatabaseServiceResponse<UserDetailsForServer> databaseServiceResponse = databaseService.getUserDetailsForServer(loginRequest.getUsername());
            UserDetailsForServer user = databaseServiceResponse.getUser();

            switch (databaseServiceResponse.getStatus()) {

                case SUCCESS:
                    if(passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
                        logger.info("Authentication successful. Username = "+user.getUsername()+" Password = "+loginRequest.getPassword());
                        return new AuthServiceResponse(
                            true,
                            "Authentication successful",
                            java.time.LocalDateTime.now(),
                            200,
                            jwtService.generateToken(user.getUsername())
                        );
                    }
                    else{
                        logger.info("Wrong Password. Username = "+user.getUsername()+" Password = "+loginRequest.getPassword());
                        return new AuthServiceResponse(
                            false, 
                            "Invalid username or password", 
                            java.time.LocalDateTime.now(), 
                            401, 
                            null)
                            ;
                    }
                case USER_NOT_FOUND:
                    logger.info("User not found. Username = "+loginRequest.getUsername());
                    return new AuthServiceResponse(
                        false,
                        "User not found",
                        java.time.LocalDateTime.now(),
                        401,
                        null
                    );
                default:
                    logger.error("An unexpected error occurred at switch case break ");
                    return new AuthServiceResponse(
                        false,
                        "An unexpected error occurred. Please try again later",
                        java.time.LocalDateTime.now(),
                        500,
                        null
                    );
            }
        }
        catch (Exception e) {
            logger.error("An unexpected error occurred "+ e);
            return new AuthServiceResponse(
                false,
                "An unexpected error occurred. Please try again later",
                java.time.LocalDateTime.now(),
                500,
                null
            );
        }
    }
}