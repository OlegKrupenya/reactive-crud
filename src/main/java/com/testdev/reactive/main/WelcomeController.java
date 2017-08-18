package com.testdev.reactive.main;

import com.testdev.reactive.main.dao.AccountDao;
import com.testdev.reactive.main.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Oleg Krupenya
 */
@RestController
public class WelcomeController {

    @Autowired
    private AccountDao accountDao;

    // TODO: use flux with delay on the server side and events on the UI
//    @GetMapping(value = "/test",produces = "application/stream+json")
    @GetMapping(value = "/test")
//    @GetMapping(value = "/test",produces = "text/event-stream")
    public Flux<Account> welcome() {
        return accountDao.findAll();
    }

    @GetMapping(value = "/account/{accountId}")
    public Mono<Account> findAccountById(@PathVariable String accountId) {
        return accountDao.findById(accountId);
    }
}