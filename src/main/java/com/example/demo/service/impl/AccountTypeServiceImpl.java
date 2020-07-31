package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.AccountType;
import com.example.demo.repo.AccountTypeRepo;
import com.example.demo.service.AccountTypeService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AccountTypeServiceImpl implements AccountTypeService {

	@Autowired
	AccountTypeRepo repo;
	
	@Override
	public Mono<AccountType> save(AccountType obj) {
		
		return repo.insert(obj);
	}

	@Override
	public Mono<AccountType> update(AccountType obj) {
		
		return repo.save(obj);
	}

	@Override
	public Flux<AccountType> findAll() {
		
		return repo.findAll();
	}

	@Override
	public Mono<AccountType> findById(Long v) {
		
		return repo.findById(v);
	}

	@Override
	public Mono<Void> deleteById(Long v) {
		
		return repo.deleteById(v);
	}

	@Override
	public Mono<Void> deleteAll() {
		
		return repo.deleteAll();
	}

}
