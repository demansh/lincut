package com.dem.lincut.persistence.sqlpersistence.repository;

import com.dem.lincut.core.adapters.LinkRepository;
import com.dem.lincut.persistence.sqlpersistence.repository.tables.daos.LinkDao;
import com.dem.lincut.persistence.sqlpersistence.repository.tables.pojos.Link;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

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
    public Optional<com.dem.lincut.core.model.Link> getByToken(String token) {
        return Optional.ofNullable(linkDao.fetchOneById(Long.valueOf(token))).map(SqlLinkRepository::toDomainLink);
    }

    @Override
    public com.dem.lincut.core.model.Link create(String url) {
        Link link = new Link();
        link.setUrl(url);
        linkDao.insert(link);
        return toDomainLink(link);
    }

    private static com.dem.lincut.core.model.Link toDomainLink(Link link) {
        return new com.dem.lincut.core.model.Link(String.valueOf(link.getId()), link.getUrl());
    }
}
