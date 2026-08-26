package com.web_atm_denemesi.atm_backend.dto;

import java.math.BigDecimal;

public class UserDetailsForClient {
    private String userName;
    private BigDecimal balance;
    
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public BigDecimal getBalance() {
        return balance;
    }
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    public UserDetailsForClient(String username, BigDecimal balance) {
        this.userName = username;
        this.balance = balance;
    }
}
