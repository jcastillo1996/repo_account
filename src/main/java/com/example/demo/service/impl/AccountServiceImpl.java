package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Account;
import com.example.demo.repo.AccountRepo;
import com.example.demo.service.AccountService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountRepo repo;
	
	@Override
	public Mono<Account> save(Account obj) {
		return repo.insert(obj);
	}

	@Override
	public Mono<Account> update(Account obj) {
		return repo.save(obj);
	}

	@Override
	public Flux<Account> findAll() {

		return repo.findAll();
	}

	@Override
	public Mono<Account> findById(Long v) {

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

	@Override
	public Flux<Account> findByIdClient(Long id) {
		
		return repo.findByIdClient(id);
	}

	@Override
	public Mono<Account> findByIdClientAndAccountType(Account account) {
		
		return repo.findByIdClientAndAccountType(account.idClient,account.accountType);
	}

}
