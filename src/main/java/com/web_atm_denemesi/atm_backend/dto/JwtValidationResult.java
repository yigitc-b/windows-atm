package com.web_atm_denemesi.atm_backend.dto;

import com.web_atm_denemesi.atm_backend.enums.ServiceResponseStatus;

public class JwtValidationResult {
    private final boolean valid;
    private final ServiceResponseStatus status;
    private final String username;

    public JwtValidationResult(boolean valid, ServiceResponseStatus status, String username) {
        this.valid = valid;
        this.status = status;
        this.username = username;
    }

    public boolean isValid() {
        return valid;
    }

    public ServiceResponseStatus getStatus() {
        return status;
    }

    public String getUsername() {
        return username;
    }
}
