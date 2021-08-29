package com.dem.lincut.core.model;

/**
 * Service to manipulate links: perform CRUD operations with validation
 */
public interface LinkService {
    Link createLink(String url);

    Link getLinkByToken(String token);
}
