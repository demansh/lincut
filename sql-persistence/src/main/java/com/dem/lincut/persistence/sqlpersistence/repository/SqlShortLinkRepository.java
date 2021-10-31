package com.dem.lincut.persistence.sqlpersistence.repository;

import com.dem.lincut.core.adapters.ShortLinkRepository;
import com.dem.lincut.core.model.ShortLink;
import com.dem.lincut.persistence.sqlpersistence.repository.tables.daos.LinkDao;
import com.dem.lincut.persistence.sqlpersistence.repository.tables.pojos.Link;
import com.dem.lincut.persistence.sqlpersistence.repository.tables.records.LinkRecord;
import org.jooq.DSLContext;
import org.jooq.SortField;
import org.jooq.TableField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.dem.lincut.persistence.sqlpersistence.repository.Tables.LINK;

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

    @Transactional(readOnly = true)
    @Override
    public Page<ShortLink> getAll(Pageable pageable) {
        List<ShortLink> links = dsl.selectFrom(LINK)
                .orderBy(getSortFields(pageable.getSort()))
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetchInto(LinkRecord.class)
                .stream()
                .map(SqlShortLinkRepository::toShortLink)
                .collect(Collectors.toList());
        long count = dsl.fetchCount(LINK);
        return new PageImpl<>(links, pageable, count);
    }

    private Collection<SortField<?>> getSortFields(Sort sortSpecification) {
        Collection<SortField<?>> querySortFields = new ArrayList<>();

        if (sortSpecification == null) {
            return querySortFields;
        }

        for (Sort.Order specifiedField : sortSpecification) {
            String sortFieldName = specifiedField.getProperty();
            Sort.Direction sortDirection = specifiedField.getDirection();

            TableField tableField = getTableField(sortFieldName);
            SortField<?> querySortField = convertTableFieldToSortField(tableField, sortDirection);
            querySortFields.add(querySortField);
        }

        return querySortFields;
    }

    private TableField getTableField(String sortFieldName) {
        TableField sortField = null;
        try {
            Field tableField = LINK.getClass().getField(sortFieldName.toUpperCase());
            sortField = (TableField) tableField.get(LINK);
        } catch (NoSuchFieldException | IllegalAccessException ex) {
            String errorMessage = String.format("Could not find table field: {}", sortFieldName);
            throw new InvalidDataAccessApiUsageException(errorMessage, ex);
        }

        return sortField;
    }

    private SortField<?> convertTableFieldToSortField(TableField tableField, Sort.Direction sortDirection) {
        if (sortDirection == Sort.Direction.ASC) {
            return tableField.asc();
        }
        else {
            return tableField.desc();
        }
    }

    private static ShortLink toShortLink(Link link) {
        return new ShortLink(String.valueOf(link.getId()), link.getUrl());
    }

    private static ShortLink toShortLink(LinkRecord link) {
        return new ShortLink(String.valueOf(link.getId()), link.getUrl());
    }
}
