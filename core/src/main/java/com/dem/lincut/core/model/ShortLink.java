package com.dem.lincut.core.model;

public class ShortLink {
    private String token;
    private String originalUrl;

    public ShortLink(String token, String originalUrl) {
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
