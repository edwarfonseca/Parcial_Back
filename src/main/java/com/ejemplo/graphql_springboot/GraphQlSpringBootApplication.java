package com.ejemplo.graphql_springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class GraphQlSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraphQlSpringBootApplication.class, args);
    }
}