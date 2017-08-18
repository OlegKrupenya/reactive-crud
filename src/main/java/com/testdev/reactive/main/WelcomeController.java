package com.testdev.reactive.main;

import com.mongodb.client.result.DeleteResult;
import com.testdev.reactive.main.dao.AccountDao;
import com.testdev.reactive.main.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    @GetMapping(value = "/accounts")
//    @GetMapping(value = "/test",produces = "text/event-stream")
    public Flux<Account> welcome() {
        return accountDao.findAll();
    }

    @GetMapping(value = "/account/{accountId}")
    public Mono<Account> findAccountById(@PathVariable String accountId) {
        return accountDao.findById(accountId);
    }

    @PutMapping(value = "/account")
    public void updateAccount(Mono<Account> account) {
        accountDao.updateAccount(account);
    }

    @PostMapping(value = "/account")
    public Mono<Account> addAccount(@RequestBody Mono<Account> account) {
        return accountDao.save(account);
    }

    @DeleteMapping(value = "/account/{accountId}")
    public Mono<DeleteResult> deleteAccount(@PathVariable String accountId) {
        return accountDao.deleteAccount(accountId);
    }
}