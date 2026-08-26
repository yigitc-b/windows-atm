package com.web_atm_denemesi.atm_backend.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.web_atm_denemesi.atm_backend.dto.UserDetailsForClient;
import com.web_atm_denemesi.atm_backend.dto.UserDetailsForServer;

@Service
public class DatabaseService {
    public UserDetailsForServer getUserDetailsForServer(String username){

        String dummyUsername="yigit";
        String dummyPassword="123";
        Long dummyuserId=123L;
        BigDecimal dummyBalance= new BigDecimal("1555");

        if(username.equals(dummyUsername)){
            return new UserDetailsForServer(dummyUsername, dummyPassword, dummyuserId, dummyBalance);
        }
        else{
            return null;
        }
    }
    public UserDetailsForClient getUserDetailsForClient(String username){

        String dummyUsername="yigit";
        BigDecimal dummyBalance= new BigDecimal("1555");

        if(username.equals(dummyUsername)){
            return new UserDetailsForClient(dummyUsername,dummyBalance);
        }
        else{
            return null;
        }
    }
}
