package com.dem.lincut.api.resources;

import com.dem.lincut.api.LinkController;
import com.dem.lincut.core.model.ShortLink;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

public class LinkModelAssembler extends RepresentationModelAssemblerSupport<ShortLink, LinkModel> {

    public LinkModelAssembler() {
        super(LinkController.class, LinkModel.class);
    }

    @Override
    public LinkModel toModel(ShortLink shortLink) {
        LinkModel linkModel = createModelWithId(shortLink.getToken(), shortLink);
        linkModel.setToken(shortLink.getToken());
        linkModel.setOriginalUlr(shortLink.getOriginalUrl());
        return linkModel;
    }
}
