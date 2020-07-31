package com.example.demo.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.demo.model.Account;

import reactor.core.publisher.Flux;

public interface AccountRepo extends ReactiveMongoRepository<Account, Long> {

	Flux<Account> findByIdClient(Long id);
	
}
