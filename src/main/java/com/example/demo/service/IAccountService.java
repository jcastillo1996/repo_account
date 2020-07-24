package com.example.demo.service;

import com.example.demo.model.Account;

import reactor.core.publisher.Mono;

public interface IAccountService extends ICRUD<Account, Long> {

	Mono<Account> findByIdClient(Long id);
	
}
