package com.dem.lincut.api;

import com.dem.lincut.api.resources.LinkModel;
import com.dem.lincut.api.resources.LinkModelAssembler;
import com.dem.lincut.core.model.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(
        path = "/links",
        produces = "application/json")
@CrossOrigin(origins = "*")
public class LinkController {
    private final LinkService linkService;

    @Autowired
    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @GetMapping
    public CollectionModel<LinkModel> getAll() {
        List<LinkModel> links = linkService.getAll().stream()
                .map(link -> new LinkModelAssembler().toModel(link))
                .collect(Collectors.toList());
        return new LinkModelAssembler().toCollectionModel(linkService.getAll());
    }

    @GetMapping("/{token}")
    public LinkModel getLink(@PathVariable("token") String token) {
        return new LinkModelAssembler().toModel(linkService.getLinkByToken(token));
    }

    @PostMapping
    public LinkModel createLink(@RequestParam("url") String url) {
        return new LinkModelAssembler().toModel(linkService.createLink(url));
    }
}
