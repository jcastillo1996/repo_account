package com.example.demo.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.demo.model.Account;
import com.example.demo.model.AccountType;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountRepo extends ReactiveMongoRepository<Account, Long> {

	@Query("{'idClient' : {$in:[?0]}}")
	Flux<Account> findByIdClient(List<Long> id);
	
	Mono<Account> findByIdClientAndAccountType(List<Long> id,AccountType type);
	
	Mono<Account> findByAccountNumber(String accountNumber);
	
}
