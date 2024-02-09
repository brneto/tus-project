package ie.ait.agile.agileproject;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;

public abstract class MySqlContainerBase {

    static final String IMAGE_TAG = "mysql:8.0.12";

    static {
        new MySQLContainer<>(IMAGE_TAG)
                .withReuse(true)
                .start();
    }

    @DynamicPropertySource
    static void mysqlProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", () -> String.format(
                "jdbc:tc:%s:///practise?TC_DAEMON=true;TC_TMPFS=/testtmpfs:rw",
                IMAGE_TAG));
    }
}
