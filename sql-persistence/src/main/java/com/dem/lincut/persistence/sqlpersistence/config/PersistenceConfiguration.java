package com.dem.lincut.persistence.sqlpersistence.config;

import com.dem.lincut.persistence.sqlpersistence.repository.tables.daos.LinkDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersistenceConfiguration {
    @Bean
    public LinkDao linkDao(org.jooq.Configuration jooqConfiguration) {
        return new LinkDao(jooqConfiguration);
    }
}
