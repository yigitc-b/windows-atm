package com.web_atm_denemesi.atm_backend.dto;

import java.math.BigDecimal;

public class UserDetailsForServer {
    private String username;
    private String password;
    private Long userId;
    private BigDecimal balance;
    
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public BigDecimal getBalance() {
        return balance;
    }
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    public UserDetailsForServer(String username, String password, Long userId, BigDecimal balance) {
        this.username = username;
        this.password = password;
        this.userId = userId;
        this.balance = balance;
    }
}
