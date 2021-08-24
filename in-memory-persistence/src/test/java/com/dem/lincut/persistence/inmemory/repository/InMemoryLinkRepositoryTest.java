package com.dem.lincut.persistence.inmemory.repository;

import com.dem.lincut.core.adapters.LinkRepository;
import com.dem.lincut.core.model.Link;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InMemoryLinkRepositoryTest {
    private LinkRepository linkRepository;

    @Before
    public void setUp() {
        linkRepository = new InMemoryLinkRepository();
    }

    @Test
    public void createLink_shouldReturnLink_whenUrlProvided() {
        String url = "http://foobar.com";

        Link result = linkRepository.create(url);

        Assert.assertEquals(url, result.getOriginalUrl());
        Assert.assertFalse(result.getToken().isEmpty());
    }

    @Test
    public void getByToken_shouldReturnLink_whenValidTokenProvided() {
        String url = "http://foobar.com";
        Link link = linkRepository.create(url);
        String token = link.getToken();

        Link result = linkRepository.getByToken(token).get();

        Assert.assertEquals(link, result);
    }

    @Test
    public void getByToken_shouldReturnNone_whenInvalidTokenProvided() {
        String token = "invalid_token";

        Assert.assertTrue(linkRepository.getByToken(token).isEmpty());
    }
}