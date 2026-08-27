package com.web_atm_denemesi.atm_backend.dto;

import java.math.BigDecimal;

public class UserDetailsForClient {
    private String username;
    private BigDecimal balance;
    
    public String getUsername() {
        return username;
    }
    public void setUsername(String userName) {
        this.username = userName;
    }
    public BigDecimal getBalance() {
        return balance;
    }
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    public UserDetailsForClient(String username, BigDecimal balance) {
        this.username = username;
        this.balance = balance;
    }
}
