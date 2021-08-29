package com.dem.lincut.test.core.model;

import com.dem.lincut.core.model.DefaultLinkService;
import com.dem.lincut.core.model.Link;
import com.dem.lincut.core.model.LinkService;
import com.dem.lincut.persistence.inmemory.repository.InMemoryLinkRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

public class LinkServiceTest {
    private LinkService linkService;

    @Before
    public void setUp() {
        linkService = new DefaultLinkService(new InMemoryLinkRepository());
    }

    @Test(expected = RuntimeException.class)
    public void createLink_shouldThrow_ifNullUrlPassed() {
        linkService.createLink(null);
    }

    @Test(expected = RuntimeException.class)
    public void createLink_shouldThrow_ifMalformedUrlPassed() {
        linkService.createLink("not-a-url");
    }

    @Test
    public void createLink_shouldNotThrow_ifValidUrlPassed() {
        linkService.createLink("https://google.com");
    }

    @Test(expected = RuntimeException.class)
    public void getByToken_shouldThrow_ifNullTokenPassed() {
        linkService.getLinkByToken(null);
    }

    @Test
    public void getByToken_shouldReturnNone_ifNoUrlFound() {
        Assert.assertEquals(Optional.empty(), linkService.getLinkByToken("42"));
    }

    @Test
    public void getByToken_shouldReturnLink_ifTokenIsValid() {
        Link link = linkService.createLink("https://google.com");

        Link result = linkService.getLinkByToken(link.getToken()).get();

        Assert.assertEquals(link, result);
    }
}
