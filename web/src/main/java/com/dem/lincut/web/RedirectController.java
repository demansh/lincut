package com.dem.lincut.web;

import com.dem.lincut.core.model.ShortLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping(path = "/r")
@CrossOrigin(origins = "*")
public class RedirectController {
    private final ShortLinkService shortLinkService;

    @Autowired
    public RedirectController(ShortLinkService shortLinkService) {
        this.shortLinkService = shortLinkService;
    }

    @GetMapping(path = "{token}")
    public RedirectView redirect(@PathVariable("token") String token) {
        String redirectUrl = shortLinkService.getLinkByToken(token).getUrl();
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(redirectUrl);
        return redirectView;
    }
}
