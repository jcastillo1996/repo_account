package com.example.demo.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.demo.model.AccountType;
import com.example.demo.model.Product;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductRepo extends ReactiveMongoRepository<Product, Long> {

	@Query("{'idClient' : {$in:[?0]}}")
	Flux<Product> findByIdClient(List<Long> id);
	
	Mono<Product> findByIdClientAndAccountType(List<Long> id,AccountType type);
}
