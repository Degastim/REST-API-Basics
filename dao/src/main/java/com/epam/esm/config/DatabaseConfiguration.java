package com.epam.esm.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
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
    private static final String CREATE_DATABASE_SCRIPT = "script/database.sql";
    private static final String INSERT_DATA_SQL = "script/data_insertion.sql";
    private static final String PACKAGE_WITH_ENTITY = "com.epam.esm.entity";


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
     * Creates SessionFactory.
     *
     * @return LocalSessionFactoryBean
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(PACKAGE_WITH_ENTITY);
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(jpaVendorAdapter);
        return em;
    }

    /**
     * Creates the transactionManager.
     *
     * @return PlatformTransactionManager object
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }
}
