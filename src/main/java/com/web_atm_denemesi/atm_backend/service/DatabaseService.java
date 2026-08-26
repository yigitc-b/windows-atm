package com.web_atm_denemesi.atm_backend.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.web_atm_denemesi.atm_backend.dto.User;

@Service
public class DatabaseService {
    public User getUser(String userName){

        String dummyUsername="yigit";
        String dummyPassword="123";
        Long dummyuserId=123L;
        BigDecimal dummyBalance= new BigDecimal("1555");

        if(userName.equals(dummyUsername)){
            return new User(dummyUsername, dummyPassword, dummyuserId, dummyBalance);
        }
        else{
            return null;
        }
    }
}
