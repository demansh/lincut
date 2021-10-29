package com.dem.lincut.api.exceptionhandlers;

public class ExceptionResource {
    private String message;

    public ExceptionResource(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
