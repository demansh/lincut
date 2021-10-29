package com.dem.lincut.core.model;

import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service to manipulate links: perform CRUD operations with validation
 */
public interface ShortLinkService {
    ShortLink createLink(String url);

    ShortLink getLinkByToken(String token);

    List<ShortLink> getAll(Pageable pageParams);
}
