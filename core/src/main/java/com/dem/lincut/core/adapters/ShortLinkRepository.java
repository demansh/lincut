package com.dem.lincut.core.adapters;

import com.dem.lincut.core.model.ShortLink;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ShortLinkRepository {
    Optional<ShortLink> getByToken(String token);

    ShortLink create(String url);

    Page<ShortLink> getAll(Pageable pageable);
}
