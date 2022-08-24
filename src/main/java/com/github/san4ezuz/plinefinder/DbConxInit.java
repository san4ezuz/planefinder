package com.github.san4ezuz.plinefinder;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

import java.time.Instant;

@Configuration
public class DbConxInit {

    @Bean
    public ConnectionFactoryInitializer
    initializer(@Qualifier("connectionFactory") ConnectionFactory connectionFactory) {
        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(connectionFactory);
        initializer.setDatabasePopulator(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));
        return initializer;
    }

    @Bean
    public CommandLineRunner init(FlightRepository repo) {
        return args -> repo.save(new Flight("SAL001", "N12345", "SUP", "SIbR"))
                .thenMany(repo.findAll())
                .subscribe(System.out::println);
    }

}
