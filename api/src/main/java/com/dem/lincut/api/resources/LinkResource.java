package com.dem.lincut.api.resources;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.ResourceSupport;

public class LinkResource extends ResourceSupport {
    @Getter
    @Setter
    private String originalUlr;

    @Getter
    @Setter
    private String token;
}
