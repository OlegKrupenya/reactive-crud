package com.testdev.reactive.main.dao;

import com.mongodb.client.result.DeleteResult;
import com.testdev.reactive.main.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Criteria.where;

/**
 * @author Oleg Krupenya
 */
@Repository
public class AccountDao {
    @Autowired
    private ReactiveMongoTemplate template;

    public Mono<Account> findById(String id) {
        return template.findById(id, Account.class);
    }

    public Flux<Account> findAll() {
        return template.findAll(Account.class);
    }

    public Flux<Account> findByCustomerId(String customerId) {
        return template.find(query(where("customerId").is(customerId)), Account.class);
    }

    public Mono<Account> save(Mono<Account> account) {
        return template.insert(account);
    }

    public void updateAccount(Mono<Account> account) {
        Update update = Update.update("id", account.block().getId());
        template.update(Account.class).matching(query(where("id").is(account.block().getId()))).apply(update);
    }

    public Mono<DeleteResult> deleteAccount(String accountId) {
        Account byId = findById(accountId).block();
        Mono<DeleteResult> resultMono = template.remove(byId);
        return resultMono;
    }
}
