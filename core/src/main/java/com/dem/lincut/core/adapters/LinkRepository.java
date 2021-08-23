package com.dem.lincut.core.adapters;

import com.dem.lincut.core.model.Link;

import java.util.Optional;

public interface LinkRepository {
    public Optional<Link> getByToken(String token);

    public Link create(String url);
}
