package com.dem.lincut.api;

import com.dem.lincut.api.resources.LinkModel;
import com.dem.lincut.api.resources.LinkModelAssembler;
import com.dem.lincut.core.model.ShortLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(
        path = "/links",
        produces = "application/json")
@CrossOrigin(origins = "*")
public class LinkController {
    private final ShortLinkService shortLinkService;

    @Autowired
    public LinkController(ShortLinkService shortLinkService) {
        this.shortLinkService = shortLinkService;
    }

    @GetMapping
    public CollectionModel<LinkModel> getAll(Pageable pageable) {
        return new LinkModelAssembler().toCollectionModel(shortLinkService.getAll(pageable));
    }

    @GetMapping("/{token}")
    public LinkModel getLink(@PathVariable("token") String token) {
        return new LinkModelAssembler().toModel(shortLinkService.getLinkByToken(token));
    }

    @PostMapping
    public LinkModel createLink(@RequestParam("url") String url) {
        return new LinkModelAssembler().toModel(shortLinkService.createLink(url));
    }
}
