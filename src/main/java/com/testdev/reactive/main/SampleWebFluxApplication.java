package com.testdev.reactive.main;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import com.testdev.reactive.main.dao.AccountDao;
import com.testdev.reactive.main.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Oleg Krupenya
 */
@SpringBootApplication
public class SampleWebFluxApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SampleWebFluxApplication.class);
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
