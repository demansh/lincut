package com.dem.lincut.api.exceptionhandlers;

import lombok.Getter;
import lombok.Setter;

public class ExceptionResource {
    @Getter
    @Setter
    private String message;

    public ExceptionResource(String message) {
        this.message = message;
    }
}
