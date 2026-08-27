package com.web_atm_denemesi.atm_backend.enums;

public enum ServiceResponseStatus {
    
    SUCCESS("deneme1"),
    FAILED("deneme2"),
    USER_NOT_FOUND("deneme3"),
    WRONG_PASSWORD("deneme4");

    private final String message;

    ServiceResponseStatus(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
