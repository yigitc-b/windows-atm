package com.web_atm_denemesi.atm_backend.dto;

import java.time.LocalDateTime;

public class ServiceResponse {
    private boolean success;
    private String message;
    private LocalDateTime timestamp;
    private String statusCode;
    private LoginRequest userData;

    public ServiceResponse(boolean success, String message, LocalDateTime timestamp, String statusCode, LoginRequest userData) {
        this.success = success;
        this.message = message;
        this.timestamp = timestamp;
        this.statusCode = statusCode;
        this.userData = userData;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public LoginRequest getUserData() {
        return userData;
    }

    public void setUserData(LoginRequest userData) {
        this.userData = userData;
    }
}
