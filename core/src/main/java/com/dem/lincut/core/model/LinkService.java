package com.dem.lincut.core.model;

import java.util.Optional;

/**
 * Service to manipulate links: perform CRUD operations with validation
 */
public interface LinkService {
    Link createLink(String url);

    Optional<Link> getLinkByToken(String token);
}
