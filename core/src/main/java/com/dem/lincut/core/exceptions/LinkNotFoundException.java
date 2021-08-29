package com.dem.lincut.core.exceptions;

public class LinkNotFoundException extends CoreException {
    public LinkNotFoundException(String token) {
        super(String.format("Link for token %s is not found", token));
    }
}
