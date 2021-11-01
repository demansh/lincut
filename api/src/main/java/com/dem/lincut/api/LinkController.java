package com.dem.lincut.api;

import com.dem.lincut.api.resources.LinkModel;
import com.dem.lincut.api.resources.LinkModelAssembler;
import com.dem.lincut.core.model.ShortLink;
import com.dem.lincut.core.model.ShortLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(
        path = "/api/v1/links",
        produces = "application/json")
@CrossOrigin(origins = "*")
public class LinkController {
    private final ShortLinkService shortLinkService;
    private final PagedResourcesAssembler<ShortLink> pagedResourcesAssembler;
    private final LinkModelAssembler linkModelAssembler;

    @Autowired
    public LinkController(ShortLinkService shortLinkService,
                          PagedResourcesAssembler<ShortLink> pagedResourcesAssembler,
                          LinkModelAssembler linkModelAssembler) {
        this.shortLinkService = shortLinkService;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
        this.linkModelAssembler = linkModelAssembler;
    }

    @GetMapping
    public PagedModel<LinkModel> getAll(Pageable pageable) {
        return pagedResourcesAssembler.toModel(shortLinkService.getAll(pageable), linkModelAssembler);
    }

    @GetMapping("/{token}")
    public LinkModel getLink(@PathVariable("token") String token) {
        return linkModelAssembler.toModel(shortLinkService.getLinkByToken(token));
    }

    @PostMapping
    public LinkModel createLink(@RequestParam("url") String url) {
        return linkModelAssembler.toModel(shortLinkService.createLink(url));
    }
}
