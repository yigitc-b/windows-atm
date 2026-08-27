package com.web_atm_denemesi.atm_backend.dto;

import java.time.LocalDateTime;

import com.web_atm_denemesi.atm_backend.enums.ServiceResponseStatus;

public class DatabaseServiceResponse<T> {
    private Boolean success;
    private ServiceResponseStatus status;
    private LocalDateTime timestamp;
    private T user;

    public DatabaseServiceResponse(Boolean success, ServiceResponseStatus status, LocalDateTime timestamp, T user) {
        this.success = success;
        this.status = status;
        this.timestamp = timestamp;
        this.user = user;
    }
    public Boolean getSuccess() {
        return success;
    }
    public void setSuccess(Boolean success) {
        this.success = success;
    }
    public ServiceResponseStatus getStatus() {
        return status;
    }
    public void setStatus(ServiceResponseStatus status) {
        this.status = status;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    public T getUser() {
        return user;
    }
    public void setUser(T user) {
        this.user = user;
    }
}

