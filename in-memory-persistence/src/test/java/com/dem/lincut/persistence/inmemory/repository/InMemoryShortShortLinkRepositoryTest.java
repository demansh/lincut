package com.dem.lincut.persistence.inmemory.repository;

import com.dem.lincut.core.adapters.ShortLinkRepository;
import com.dem.lincut.core.model.ShortLink;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InMemoryShortShortLinkRepositoryTest {
    private ShortLinkRepository shortLinkRepository;

    @Before
    public void setUp() {
        shortLinkRepository = new InMemoryShortLinkRepository();
    }

    @Test
    public void createLink_shouldReturnLink_whenUrlProvided() {
        String url = "http://foobar.com";

        ShortLink result = shortLinkRepository.create(url);

        Assert.assertEquals(url, result.getOriginalUrl());
        Assert.assertFalse(result.getToken().isEmpty());
    }

    @Test
    public void getByToken_shouldReturnLink_whenValidTokenProvided() {
        String url = "http://foobar.com";
        ShortLink shortLink = shortLinkRepository.create(url);
        String token = shortLink.getToken();

        ShortLink result = shortLinkRepository.getByToken(token).get();

        Assert.assertEquals(shortLink, result);
    }

    @Test
    public void getByToken_shouldReturnNone_whenInvalidTokenProvided() {
        String token = "invalid_token";

        Assert.assertTrue(shortLinkRepository.getByToken(token).isEmpty());
    }
}