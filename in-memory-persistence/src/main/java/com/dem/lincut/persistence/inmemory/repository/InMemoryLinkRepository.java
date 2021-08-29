package com.dem.lincut.persistence.inmemory.repository;

import com.dem.lincut.core.adapters.LinkRepository;
import com.dem.lincut.core.model.Link;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryLinkRepository implements LinkRepository {
    private final Map<String, Link> repository = new ConcurrentHashMap<>();

    @Override
    public Optional<Link> getByToken(String token) {
        return Optional.ofNullable(repository.getOrDefault(token, null));
    }

    @Override
    public Link create(String url) {
        String token = generateToken();
        Link link = new Link(token, url);
        boolean savedSuccessfully = repository.putIfAbsent(token, link) == null;
        if (savedSuccessfully) {
            return link;
        } else {
            throw new RuntimeException("Failed to generate a unique token");
        }
    }

    private String generateToken() {
        String token = RandomStringUtils.randomAlphanumeric(8);
        int retires = 3;
        while (retires > 0 && repository.containsKey(token)) {
            token = RandomStringUtils.randomAlphanumeric(8);
            retires --;
        }
        if (retires == 0) {
            throw new RuntimeException("Failed to generate a unique token");
        }
        return token;
    }
}
