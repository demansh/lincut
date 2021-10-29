package com.dem.lincut.test.core.model;

import com.dem.lincut.core.exceptions.InvalidParameterException;
import com.dem.lincut.core.exceptions.LinkNotFoundException;
import com.dem.lincut.core.model.DefaultShortLinkService;
import com.dem.lincut.core.model.ShortLink;
import com.dem.lincut.core.model.ShortLinkService;
import com.dem.lincut.persistence.inmemory.repository.InMemoryShortLinkRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ShortShortLinkServiceTest {
    private ShortLinkService shortLinkService;

    @Before
    public void setUp() {
        shortLinkService = new DefaultShortLinkService(new InMemoryShortLinkRepository());
    }

    @Test(expected = InvalidParameterException.class)
    public void createLink_shouldThrow_ifNullUrlPassed() {
        shortLinkService.createLink(null);
    }

    @Test(expected = InvalidParameterException.class)
    public void createLink_shouldThrow_ifMalformedUrlPassed() {
        shortLinkService.createLink("not-a-url");
    }

    @Test
    public void createLink_shouldNotThrow_ifValidUrlPassed() {
        shortLinkService.createLink("https://google.com");
    }

    @Test(expected = InvalidParameterException.class)
    public void getByToken_shouldThrow_ifNullTokenPassed() {
        shortLinkService.getLinkByToken(null);
    }

    @Test(expected = LinkNotFoundException.class)
    public void getByToken_shouldThrow_ifNoUrlFound() {
        shortLinkService.getLinkByToken("42");
    }

    @Test
    public void getByToken_shouldReturnLink_ifTokenIsValid() {
        ShortLink shortLink = shortLinkService.createLink("https://google.com");

        ShortLink result = shortLinkService.getLinkByToken(shortLink.getToken());

        Assert.assertEquals(shortLink, result);
    }
}
