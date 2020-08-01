package com.example.demo.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.demo.model.Account;
import com.example.demo.model.AccountType;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountRepo extends ReactiveMongoRepository<Account, Long> {

	Flux<Account> findByIdClient(Long id);
	
	Mono<Account> findByIdClientAndAccountType(Long id,AccountType type);
	
}
