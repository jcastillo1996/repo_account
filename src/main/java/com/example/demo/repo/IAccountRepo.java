package com.example.demo.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.demo.model.Account;

import reactor.core.publisher.Mono;

public interface IAccountRepo extends ReactiveMongoRepository<Account, Long> {

	Mono<Account> findByIdClient(Long id);
	
}
