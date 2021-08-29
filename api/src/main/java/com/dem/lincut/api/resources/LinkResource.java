package com.dem.lincut.api.resources;

import com.dem.lincut.core.model.Link;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;

public class LinkResource extends ResourceSupport {
    @Getter
    private final String originalUlr;

    @Getter
    private final String token;

    public LinkResource(Link link) {
        this.originalUlr = link.getOriginalUrl();
        this.token = link.getToken();
    }
}
