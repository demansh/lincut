package com.dem.lincut.persistence.sqlpersistence.config;

import com.dem.lincut.persistence.sqlpersistence.repository.tables.daos.LinkDao;
import org.jooq.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

import javax.sql.DataSource;

@Configuration
public class PersistenceConfiguration {
    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public DataSourceConnectionProvider connectionProvider(@Autowired DataSource dataSource) {
        return new DataSourceConnectionProvider
                (new TransactionAwareDataSourceProxy(dataSource));
    }

    @Bean
    public DefaultConfiguration configuration(@Autowired DataSourceConnectionProvider connectionProvider) {
        DefaultConfiguration jooqConfiguration = new DefaultConfiguration();
        jooqConfiguration.set(connectionProvider);
        jooqConfiguration
                .set(new DefaultExecuteListenerProvider(new DefaultExecuteListener()));
        return jooqConfiguration;
    }

    @Bean
    public LinkDao linkDao(DefaultConfiguration jooqConfiguration) {
        return new LinkDao(jooqConfiguration);
    }

    @Bean
    public DefaultDSLContext dsl(@Autowired DefaultConfiguration configuration) {
        return new DefaultDSLContext(configuration);
    }
}
