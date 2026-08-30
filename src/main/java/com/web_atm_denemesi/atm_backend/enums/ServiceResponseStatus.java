package com.web_atm_denemesi.atm_backend.enums;

public enum ServiceResponseStatus {
    
    VALID("Token başarıyla doğrulandı."),
    SUCCESS("deneme1"),
    FAILED("deneme2"),
    USER_NOT_FOUND("deneme3"),
    WRONG_PASSWORD("deneme4"),
    EMPTY_OR_NULL("deneme5"),
    EXPIRED("denene6"),
    MALFORMED("Token formatı bozuk veya geçersiz."),
    INVALID_SIGNATURE("Token imzası doğrulanamadı."),
    UNSUPPORTED("Desteklenmeyen JWT token yapısı.");

    private final String message;

    ServiceResponseStatus(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
