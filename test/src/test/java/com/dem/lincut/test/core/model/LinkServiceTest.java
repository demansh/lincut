package com.dem.lincut.test.core.model;

import com.dem.lincut.core.exceptions.InvalidParameterException;
import com.dem.lincut.core.exceptions.LinkNotFoundException;
import com.dem.lincut.core.model.DefaultLinkService;
import com.dem.lincut.core.model.Link;
import com.dem.lincut.core.model.LinkService;
import com.dem.lincut.persistence.inmemory.repository.InMemoryLinkRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LinkServiceTest {
    private LinkService linkService;

    @Before
    public void setUp() {
        linkService = new DefaultLinkService(new InMemoryLinkRepository());
    }

    @Test(expected = InvalidParameterException.class)
    public void createLink_shouldThrow_ifNullUrlPassed() {
        linkService.createLink(null);
    }

    @Test(expected = InvalidParameterException.class)
    public void createLink_shouldThrow_ifMalformedUrlPassed() {
        linkService.createLink("not-a-url");
    }

    @Test
    public void createLink_shouldNotThrow_ifValidUrlPassed() {
        linkService.createLink("https://google.com");
    }

    @Test(expected = InvalidParameterException.class)
    public void getByToken_shouldThrow_ifNullTokenPassed() {
        linkService.getLinkByToken(null);
    }

    @Test(expected = LinkNotFoundException.class)
    public void getByToken_shouldThrow_ifNoUrlFound() {
        linkService.getLinkByToken("42");
    }

    @Test
    public void getByToken_shouldReturnLink_ifTokenIsValid() {
        Link link = linkService.createLink("https://google.com");

        Link result = linkService.getLinkByToken(link.getToken());

        Assert.assertEquals(link, result);
    }
}
