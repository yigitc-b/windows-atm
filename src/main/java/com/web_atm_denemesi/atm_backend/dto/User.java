package com.web_atm_denemesi.atm_backend.dto;

import java.math.BigDecimal;

public class User {
    private String userName;
    private String password;
    private Long userId;
    private BigDecimal balance;
    
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
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
    public User(String userName, String password, Long userId, BigDecimal balance) {
        this.userName = userName;
        this.password = password;
        this.userId = userId;
        this.balance = balance;
    }
}
