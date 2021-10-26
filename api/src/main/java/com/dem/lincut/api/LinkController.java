package com.dem.lincut.api;

import com.dem.lincut.api.resources.LinkModel;
import com.dem.lincut.api.resources.LinkModelAssembler;
import com.dem.lincut.core.model.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{token}")
    public LinkModel getLink(@PathVariable("token") String token) {
        return new LinkModelAssembler().toModel(linkService.getLinkByToken(token));
    }

    @PostMapping
    public LinkModel createLink(@RequestParam("url") String url) {
        return new LinkModelAssembler().toModel(linkService.createLink(url));
    }
}
