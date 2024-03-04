package com.YogApp.app.Exception;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ErrorResponse {
    private String message;
    private LocalDateTime date;

    public ErrorResponse(String message){
        this.message = message;
        this.date = LocalDateTime.now();
    }
}
