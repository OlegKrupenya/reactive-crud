package com.testdev.reactive.main;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

/**
 * @author Oleg Krupenya
 */
@SpringBootApplication
public class AccountWebFluxApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(AccountWebFluxApplication.class);
    }

    public @Bean
    MongoClient mongoClient() {
        return MongoClients.create("mongodb://127.0.0.1");
    }

    public @Bean
    ReactiveMongoTemplate reactiveMongoTemplate() {
        return new ReactiveMongoTemplate(mongoClient(), "reactive");
    }
}
