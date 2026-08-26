package com.web_atm_denemesi.atm_backend.service;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.KeyStore.SecretKeyEntry;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import tools.jackson.core.ObjectReadContext.Base;

@Service
public class JWTService {

    private String secretKey="";

    public JWTService(){
        try {
            KeyGenerator keyGen=KeyGenerator.getInstance("HmacSHA256");
            SecretKey sk =keyGen.generateKey();
            secretKey=Base64.getEncoder().encodeToString(sk.getEncoded());

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    
    public String generateToken(String userName){
        
        Map<String, Object>claims=new HashMap<>();

        System.out.println("Secret Signkey "+getSecretKey());
        
        return Jwts.builder()
            .claims()
            .add(claims)
            .subject(userName)
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(System.currentTimeMillis()+60*60*30))
            .and()
            .signWith(getKey())
            .compact();
    }

    private Key getKey() {
        byte[]keyBytes=Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    public String getSecretKey(){
        return secretKey;
    }
}
