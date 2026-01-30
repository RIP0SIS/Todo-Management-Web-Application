package com.first.springboot.firstwebapp.todo.exception;

import java.time.LocalDateTime;

public class ErrorDetails {
    //Video : 16
    private LocalDateTime timeStamps;
    private String message;
    private String details;

    public ErrorDetails(LocalDateTime timeStamps, String message, String details) {
        this.timeStamps = timeStamps;
        this.message = message;
        this.details = details;
    }

    public LocalDateTime getTimeStamps() {
        return timeStamps;
    }

    public void setTimeStamps(LocalDateTime timeStamps) {
        this.timeStamps = timeStamps;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
