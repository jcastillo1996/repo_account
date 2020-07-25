package com.example.demo.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.model.Account;
import com.example.demo.service.impl.AccountServiceImpl;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api_account")
public class AccountController {

	@Autowired
	AccountServiceImpl service;
	
	@GetMapping
	public Flux<Account> findAll() {

		return service.findAll();
	}

	@GetMapping("/{id}")
	public Mono<Account> findById(@PathVariable(name = "id") Long id) {
		return service.findById(id).switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,"ACCOUNT NOT FOUND ID: "+id)));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Account> save(@RequestBody  Account account) {
		 return service.save(account)
				 ;
	}

	@PutMapping
	public Mono<Account> update(@RequestBody(required = true) Account account) {
		account.creationDate=LocalDate.now();
		return service.update(account);
	}

	@DeleteMapping("/{id}")
	public Mono<Void> deleteById(@PathVariable Long id) {

		return service.deleteById(id);
	}

	@DeleteMapping("/deleteAll")
	public Mono<Void> deleteAll() {

		return service.deleteAll();
	}

	
}
