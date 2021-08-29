package com.dem.lincut.api.resources;

import com.dem.lincut.api.LinkController;
import com.dem.lincut.core.model.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public class LinkResourceAssembler extends
        ResourceAssemblerSupport<Link, LinkResource> {

    public LinkResourceAssembler() {
        super(LinkController.class, LinkResource.class);
    }

    @Override
    public LinkResource toResource(Link link) {
        return createResourceWithId(link.getToken(), link);
    }
}
