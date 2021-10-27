package com.dem.lincut.api.resources;

import org.springframework.hateoas.RepresentationModel;

public class LinkModel extends RepresentationModel<LinkModel> {
    private String originalUlr;
    private String token;

    public String getOriginalUlr() {
        return originalUlr;
    }

    public void setOriginalUlr(String originalUlr) {
        this.originalUlr = originalUlr;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
