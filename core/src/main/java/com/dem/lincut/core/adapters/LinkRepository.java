package com.dem.lincut.core.adapters;

import com.dem.lincut.core.model.ShortLink;

import java.util.List;
import java.util.Optional;

public interface LinkRepository {
    Optional<ShortLink> getByToken(String token);

    ShortLink create(String url);

    List<ShortLink> getAll();
}
