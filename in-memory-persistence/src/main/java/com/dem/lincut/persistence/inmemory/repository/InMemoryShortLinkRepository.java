package com.dem.lincut.persistence.inmemory.repository;

import com.dem.lincut.core.adapters.ShortLinkRepository;
import com.dem.lincut.core.model.ShortLink;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryShortLinkRepository implements ShortLinkRepository {
    private final Map<String, ShortLink> repository = new ConcurrentHashMap<>();

    @Override
    public Optional<ShortLink> getByToken(String token) {
        return Optional.ofNullable(repository.getOrDefault(token, null));
    }

    @Override
    public ShortLink create(String url) {
        String token = generateToken();
        ShortLink shortLink = new ShortLink(token, url);
        boolean savedSuccessfully = repository.putIfAbsent(token, shortLink) == null;
        if (savedSuccessfully) {
            return shortLink;
        } else {
            throw new RuntimeException("Failed to generate a unique token");
        }
    }

    @Override
    public List<ShortLink> getAll(Pageable pageable) {
        return new ArrayList<>(repository.values());
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
