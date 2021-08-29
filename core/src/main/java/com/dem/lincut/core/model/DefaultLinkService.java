package com.dem.lincut.core.model;

import com.dem.lincut.core.adapters.LinkRepository;
import com.dem.lincut.core.exceptions.InvalidParameterException;
import com.dem.lincut.core.exceptions.LinkNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;

@Service
public class DefaultLinkService implements LinkService {
    private final LinkRepository linkRepository;

    @Autowired
    public DefaultLinkService(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    @Override
    public Link createLink(String url) {
        if (url == null) {
            throw new InvalidParameterException("Null url passed");
        }
        try {
            URL validUrl = new URL(url);
            return linkRepository.create(validUrl.toString());
        } catch (MalformedURLException e) {
            throw new InvalidParameterException("Malformed url");
        }
    }

    @Override
    public Link getLinkByToken(String token) {
        if (token == null) {
            throw new InvalidParameterException("Null token passed");
        }
        return linkRepository
                .getByToken(token)
                .orElseThrow(() -> new LinkNotFoundException(token));
    }
}
