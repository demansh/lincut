package com.dem.lincut.api.resources;

import org.springframework.hateoas.RepresentationModel;

public class LinkModel extends RepresentationModel<LinkModel> {
    private String url;
    private String token;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
