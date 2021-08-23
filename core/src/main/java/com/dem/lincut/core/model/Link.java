package com.dem.lincut.core.model;

public class Link {
    private String token;
    private String originalUrl;

    public Link(String token, String originalUrl) {
        this.token = token;
        this.originalUrl = originalUrl;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }
}
