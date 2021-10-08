package com.epam.esm.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Database configuration class.
 *
 * @author Yauheni Tstiov
 */
@Configuration
@EnableTransactionManagement
public class DatabaseConfiguration {
    private static final String DATABASE_PROPERTY_FILE_PATH = "/databaseConfig.properties";
    private static final String CREATE_DATABASE_SCRIPT = "classpath:script/database.sql";
    private static final String INSERT_DATA_SQL = "script/data_insertion.sql";

    /**
     * Creates the data source for dev profile.
     *
     * @return data source object
     */
    @Bean("dataSource")
    @Profile("prod")
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig(DATABASE_PROPERTY_FILE_PATH);
        return new HikariDataSource(config);
    }

    /**
     * Creates the data source for prod profile.
     *
     * @return data source object
     */
    @Bean("dataSource")
    @Profile("dev")
    public DataSource embeddedDataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript(CREATE_DATABASE_SCRIPT)
                .addScript(INSERT_DATA_SQL)
                .build();
    }

    /**
     * Creates the jdbcTemplate.
     *
     * @return JdbcTemplate object
     */
    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    /**
     * Creates the transactionManager.
     *
     * @return PlatformTransactionManager object
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }
}
