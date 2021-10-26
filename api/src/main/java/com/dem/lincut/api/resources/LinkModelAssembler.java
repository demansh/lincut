package com.dem.lincut.api.resources;

import com.dem.lincut.api.LinkController;
import com.dem.lincut.core.model.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

public class LinkModelAssembler extends RepresentationModelAssemblerSupport<Link, LinkModel> {

    public LinkModelAssembler() {
        super(LinkController.class, LinkModel.class);
    }

    @Override
    public LinkModel toModel(Link link) {
        LinkModel linkModel = createModelWithId(link.getToken(), link);
        linkModel.setToken(link.getToken());
        linkModel.setOriginalUlr(link.getOriginalUrl());
        return linkModel;
    }
}
