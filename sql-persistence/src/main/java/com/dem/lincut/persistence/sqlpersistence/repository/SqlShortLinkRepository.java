package com.dem.lincut.persistence.sqlpersistence.repository;

import com.dem.lincut.core.adapters.ShortLinkRepository;
import com.dem.lincut.core.model.ShortLink;
import com.dem.lincut.persistence.sqlpersistence.repository.tables.daos.LinkDao;
import com.dem.lincut.persistence.sqlpersistence.repository.tables.pojos.Link;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class SqlShortLinkRepository implements ShortLinkRepository {
    private final LinkDao linkDao;
    private final DSLContext dsl;

    @Autowired
    public SqlShortLinkRepository(LinkDao linkDao, DSLContext dsl) {
        this.linkDao = linkDao;
        this.dsl = dsl;
    }

    @Override
    public Optional<ShortLink> getByToken(String token) {
        return Optional.ofNullable(linkDao.fetchOneById(Long.valueOf(token))).map(SqlShortLinkRepository::toShortLink);
    }

    @Override
    public ShortLink create(String url) {
        Link link = new Link();
        link.setUrl(url);
        linkDao.insert(link);
        return toShortLink(link);
    }

    @Override
    public List<ShortLink> getAll(Pageable pageable) {
        return linkDao.findAll().stream().map(SqlShortLinkRepository::toShortLink).collect(Collectors.toList());
    }

    private static ShortLink toShortLink(Link link) {
        return new ShortLink(String.valueOf(link.getId()), link.getUrl());
    }
}
