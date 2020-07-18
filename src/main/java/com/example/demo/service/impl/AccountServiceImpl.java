package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Account;
import com.example.demo.repo.IAccountRepo;
import com.example.demo.service.IAccountService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AccountServiceImpl implements IAccountService {

	@Autowired
	IAccountRepo repo;

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

}
