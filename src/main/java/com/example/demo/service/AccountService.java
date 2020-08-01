package com.example.demo.service;

import com.example.demo.model.Account;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountService extends ICRUD<Account, Long> {

	Flux<Account> findByIdClient(Long id);
	
	Mono<Account> findByIdClientAndAccountType(Account account);
	
}
