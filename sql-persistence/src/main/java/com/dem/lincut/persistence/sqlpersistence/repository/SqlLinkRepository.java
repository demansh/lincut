package com.dem.lincut.persistence.sqlpersistence.repository;

import com.dem.lincut.core.adapters.LinkRepository;
import com.dem.lincut.core.model.ShortLink;
import com.dem.lincut.persistence.sqlpersistence.repository.tables.daos.LinkDao;
import com.dem.lincut.persistence.sqlpersistence.repository.tables.pojos.Link;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class SqlLinkRepository implements LinkRepository {
    private final LinkDao linkDao;
    private final DSLContext dsl;

    @Autowired
    public SqlLinkRepository(LinkDao linkDao, DSLContext dsl) {
        this.linkDao = linkDao;
        this.dsl = dsl;
    }

    @Override
    public Optional<ShortLink> getByToken(String token) {
        return Optional.ofNullable(linkDao.fetchOneById(Long.valueOf(token))).map(SqlLinkRepository::toShortLink);
    }

    @Override
    public ShortLink create(String url) {
        Link link = new Link();
        link.setUrl(url);
        linkDao.insert(link);
        return toShortLink(link);
    }

    public List<ShortLink> getAll() {
        return linkDao.findAll().stream().map(SqlLinkRepository::toShortLink).collect(Collectors.toList());
    }

    private static ShortLink toShortLink(Link link) {
        return new ShortLink(String.valueOf(link.getId()), link.getUrl());
    }
}
