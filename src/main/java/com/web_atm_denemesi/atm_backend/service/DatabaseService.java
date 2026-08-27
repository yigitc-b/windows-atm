package com.web_atm_denemesi.atm_backend.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.web_atm_denemesi.atm_backend.dto.DatabaseServiceResponse;
import com.web_atm_denemesi.atm_backend.dto.UserDetailsForClient;
import com.web_atm_denemesi.atm_backend.dto.UserDetailsForServer;
import com.web_atm_denemesi.atm_backend.enums.ServiceResponseStatus;

@Service
public class DatabaseService {
    public DatabaseServiceResponse<UserDetailsForServer> getUserDetailsForServer(String username){

        String dummyUsername="yigit";
        String dummyPassword="$2a$12$Y4ywm1qd7T43A4KSOQxXduahH6gJe6qGMjfseJnoqH222Epgpn67i";
        Long dummyuserId=123L;
        BigDecimal dummyBalance= new BigDecimal("1555");

        if(username.equals(dummyUsername)){
            return new DatabaseServiceResponse<>(
                true,
                ServiceResponseStatus.SUCCESS,
                java.time.LocalDateTime.now(),
                new UserDetailsForServer(dummyUsername, dummyPassword, dummyuserId, dummyBalance)
            );
        }
        else{
            return new DatabaseServiceResponse<>(
                    false,
                    ServiceResponseStatus.USER_NOT_FOUND,
                    java.time.LocalDateTime.now(),
                    null
                );
        }
    }
    public DatabaseServiceResponse<UserDetailsForClient> getUserDetailsForClient(String username){

        String dummyUsername="yigit";
        BigDecimal dummyBalance= new BigDecimal("1555");

        if(username.equals(dummyUsername)){
            return new DatabaseServiceResponse<>(
                true,
                ServiceResponseStatus.SUCCESS,
                java.time.LocalDateTime.now(),
                new UserDetailsForClient(dummyUsername, dummyBalance)
            );
        }
        else{
            return new DatabaseServiceResponse<>(
                    false,
                    ServiceResponseStatus.USER_NOT_FOUND,
                    java.time.LocalDateTime.now(),
                    null
                );
        }
    }
}
