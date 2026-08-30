package com.web_atm_denemesi.atm_backend.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.web_atm_denemesi.atm_backend.dto.JwtValidationResult;
import com.web_atm_denemesi.atm_backend.enums.ServiceResponseStatus;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
//LOGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGING
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//LOGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGING

@Service
public class JWTService {

    //LOGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGING
    private static final Logger logger = LoggerFactory.getLogger(JWTService.class);
    //LOGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGING

    @Value("${jwt.secret-key}")
    private String secretKey;
    
    public String generateToken(String userName){
        
        Map<String, Object>claims=new HashMap<>();

        logger.info("Secret Signkey "+getSecretKey());
        
        return Jwts.builder()
            .claims()
            .add(claims)
            .subject(userName)
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 10))
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

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        if(claims==null){
            logger.warn("Claims nesnesi null döndüğü için claim çıkarılamadı.");
            return null;
        }
        return claimsResolver.apply(claims);
    }

    public JwtValidationResult validateToken(String token) {

        if(token == null || token.trim().isEmpty()){
            return new JwtValidationResult(false, ServiceResponseStatus.EMPTY_OR_NULL, null);
        }

        try {

            Claims claims = Jwts.parser()
            .verifyWith((SecretKey) getKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();

            String username = claims.getSubject();
            return new JwtValidationResult(true, ServiceResponseStatus.VALID, username);
            
        }catch(Exception e){
            ServiceResponseStatus status = switch (e) {
                case ExpiredJwtException ex -> ServiceResponseStatus.EXPIRED;
                case MalformedJwtException ex -> ServiceResponseStatus.MALFORMED;
                case SignatureException ex -> ServiceResponseStatus.INVALID_SIGNATURE;
                case UnsupportedJwtException ex -> ServiceResponseStatus.UNSUPPORTED;
                default -> ServiceResponseStatus.MALFORMED;
            };
            return new JwtValidationResult(false, status, null);
        }
    }
    
    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parser()
                    .verifyWith((SecretKey) getKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (ExpiredJwtException e) {
            logger.debug("Token süresi dolduğu için Claims bilgisi exception üzerinden alındı.");
            return e.getClaims();
        } catch (JwtException | IllegalArgumentException e) {
            logger.error("Claims extract edilirken token bozuk olduğu için okunamadı: {}", e.getMessage());
            return null;
        }
    }
}
