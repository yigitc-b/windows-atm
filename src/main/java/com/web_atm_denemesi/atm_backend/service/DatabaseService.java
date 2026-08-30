package com.web_atm_denemesi.atm_backend.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.web_atm_denemesi.atm_backend.dto.UserDetailsForServer;
import com.web_atm_denemesi.atm_backend.exception.UserNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DatabaseService {
    public UserDetailsForServer getUserDetailsForServer(String username){

        String dummyUsername="yigit";
        String dummyPassword="$2a$12$Y4ywm1qd7T43A4KSOQxXduahH6gJe6qGMjfseJnoqH222Epgpn67i";
        Long dummyuserId=123L;
        BigDecimal dummyBalance= new BigDecimal("1555");

        if(username.equals(dummyUsername)){
            return new UserDetailsForServer(dummyUsername, dummyPassword, dummyuserId, dummyBalance);
        }
        else{
            throw new UserNotFoundException("User not found: "+ username);
        }
    }
}
