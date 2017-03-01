package de.springbootbuch.zip_layout;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class Application {

    @Component
    public static class Cli implements CommandLineRunner {

        private static final Logger LOG = LoggerFactory.getLogger(Cli.class);
        
        private final JdbcTemplate jdbcTemplate;

        public Cli(JdbcTemplate jdbcTemplate) {
            this.jdbcTemplate = jdbcTemplate;
        }
        
        @Override
        public void run(String... args) throws Exception {
            LOG.info("Life is {}", 
                    this.jdbcTemplate.queryForObject("Select 'life' from dual", String.class));
        }
    }

    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }
}