package com.ecmp.api;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;

public abstract class AbstractContainerBaseTest {
    static final PostgreSQLContainer postgresqlContainer;

    static {
        postgresqlContainer = (PostgreSQLContainer) new PostgreSQLContainer("postgres:16.0-alpine")
                .withDatabaseName("ecmp")
                .withUsername("developer")
                .withPassword("postgres")
                .withReuse(true)
                .waitingFor(Wait.forLogMessage(".*database system is ready to accept connections.*", 1));
        postgresqlContainer.start();
    }

    @DynamicPropertySource
    static void registerPostgresProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresqlContainer::getJdbcUrl);
    }
}
