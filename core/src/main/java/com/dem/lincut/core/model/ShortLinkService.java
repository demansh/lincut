package com.dem.lincut.core.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service to manipulate links: perform CRUD operations with validation
 */
public interface ShortLinkService {
    ShortLink createLink(String url);

    ShortLink getLinkByToken(String token);

    Page<ShortLink> getAll(Pageable pageParams);
}
