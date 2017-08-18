package com.testdev.reactive.main;

import com.mongodb.client.result.DeleteResult;
import com.testdev.reactive.main.dao.AccountDao;
import com.testdev.reactive.main.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Oleg Krupenya
 */
@RestController
public class AccountController {

    @Autowired
    private AccountDao accountDao;

    @GetMapping(value = "/accounts")
    public Flux<Account> findAccounts() {
        return accountDao.findAll();
    }

    @GetMapping(value = "/account/{accountId}")
    public Mono<Account> findAccountById(@PathVariable String accountId) {
        return accountDao.findById(accountId);
    }

    @GetMapping(value = "/accounts/customer/{customerId}")
    public Flux<Account> findAccountByCustomerId(@PathVariable String customerId) {
        return accountDao.findByCustomerId(customerId);
    }

    @PutMapping(value = "/account")
    public Mono<Account> updateAccount(@RequestBody Mono<Account> account) {
        return accountDao.update(account);
    }

    @PostMapping(value = "/account")
    public Mono<Account> addAccount(@RequestBody Mono<Account> account) {
        return accountDao.save(account);
    }

    @DeleteMapping(value = "/account/{accountId}")
    public Mono<DeleteResult> deleteAccount(@PathVariable String accountId) {
        return accountDao.delete(accountId);
    }
}