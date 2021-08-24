package com.dem.lincut.core.model;

import com.dem.lincut.core.adapters.LinkRepository;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;

public class DefaultLinkService implements LinkService {
    private final LinkRepository linkRepository;

    public DefaultLinkService(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    @Override
    public Link createLink(String url) {
        Objects.requireNonNull(url, "Null url passed");
        try {
            URL validUrl = new URL(url);
            return linkRepository.create(validUrl.toString());
        } catch (MalformedURLException e) {
            throw new RuntimeException("Malformed url");
        }
    }

    @Override
    public Optional<Link> getLinkByToken(String token) {
        Objects.requireNonNull(token, "Null token passed");
        return linkRepository.getByToken(token);
    }
}
