package com.epam.esm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class Application {
    private static final String CREATE_DATABASE_SCRIPT = "script/database.sql";
    private static final String INSERT_DATA_SQL = "script/data_insertion.sql";

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean("dataSource")
    public DataSource embeddedDataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript(CREATE_DATABASE_SCRIPT)
                .addScript(INSERT_DATA_SQL)
                .build();
    }
}