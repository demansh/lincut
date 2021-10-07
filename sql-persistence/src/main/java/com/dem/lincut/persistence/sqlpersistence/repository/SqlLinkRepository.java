package com.dem.lincut.persistence.sqlpersistence.repository;

import com.dem.lincut.core.adapters.LinkRepository;
import com.dem.lincut.core.model.Link;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class SqlLinkRepository implements LinkRepository {
    @Override
    public Optional<Link> getByToken(String token) {
        return Optional.empty();
    }

    @Override
    public Link create(String url) {
        return null;
    }
}
