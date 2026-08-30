package com.web_atm_denemesi.atm_backend.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.web_atm_denemesi.atm_backend.dto.JwtValidationResult;
import com.web_atm_denemesi.atm_backend.service.AuthService;
import com.web_atm_denemesi.atm_backend.service.JWTService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//LOGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGING
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//LOGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGING

@Component
public class JwtFilter extends OncePerRequestFilter{
    //LOGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGING
    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);
    //LOGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGING

    @Autowired
    private JWTService jwtService;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
    
        return path.startsWith("/api/no-auth/");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        
        logger.info("A request send");

        String authHeader = request.getHeader("Authorization");
        
        if(authHeader != null && authHeader.startsWith("Bearer ")){
            String token = authHeader.substring(7);

            JwtValidationResult result = jwtService.validateToken(token);

            if(result.isValid()){

                String username = jwtService.extractUsername(token);
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    null
                );
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
                logger.info("AUTH SUCCESS");
            }
            else{
                logger.error(result.getStatus().toString());
            }
        }
        filterChain.doFilter(request, response);
    }
}
