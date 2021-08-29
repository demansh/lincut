package com.dem.lincut.api;

import com.dem.lincut.api.resources.LinkResource;
import com.dem.lincut.api.resources.LinkResourceAssembler;
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
    public LinkResource getLink(@PathVariable("token") String token) {
        return new LinkResourceAssembler()
                .toResource(linkService.getLinkByToken(token));
    }

    @PostMapping
    public LinkResource createLink(@RequestParam("url") String url) {
        return new LinkResourceAssembler()
                .toResource(linkService.createLink(url));
    }
}
