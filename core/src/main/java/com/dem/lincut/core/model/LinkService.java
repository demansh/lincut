package com.dem.lincut.core.model;

import java.util.List;

/**
 * Service to manipulate links: perform CRUD operations with validation
 */
public interface LinkService {
    ShortLink createLink(String url);

    ShortLink getLinkByToken(String token);

    List<ShortLink> getAll();
}
