package com.dem.lincut.core.model;

import com.dem.lincut.core.adapters.ShortLinkRepository;
import com.dem.lincut.core.exceptions.InvalidParameterException;
import com.dem.lincut.core.exceptions.LinkNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;

@Service
public class DefaultShortLinkService implements ShortLinkService {
    private final ShortLinkRepository shortLinkRepository;

    @Autowired
    public DefaultShortLinkService(ShortLinkRepository shortLinkRepository) {
        this.shortLinkRepository = shortLinkRepository;
    }

    @Override
    public ShortLink createLink(String url) {
        if (url == null) {
            throw new InvalidParameterException("Null url passed");
        }
        try {
            URL validUrl = new URL(url);
            return shortLinkRepository.create(validUrl.toString());
        } catch (MalformedURLException e) {
            throw new InvalidParameterException("Malformed url");
        }
    }

    @Override
    public ShortLink getLinkByToken(String token) {
        if (token == null) {
            throw new InvalidParameterException("Null token passed");
        }
        return shortLinkRepository
                .getByToken(token)
                .orElseThrow(() -> new LinkNotFoundException(token));
    }

    @Override
    public Page<ShortLink> getAll(Pageable pageable) {
        return shortLinkRepository.getAll(pageable);
    }
}
