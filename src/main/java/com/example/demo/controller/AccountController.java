package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
		return service.findById(id).switchIfEmpty(
				Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "ACCOUNT NOT FOUND ID: " + id)));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<?> save(@RequestBody Account account) {
		return service.findByIdClientAndAccountType(account).flatMap(acc -> {
			return Mono.empty().switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"CUSTOMER WITH ID " + acc.idClient + " HAS A REGISTERED ACCOUNT OF THE SAME TYPE")));
		}).switchIfEmpty(Mono.defer(() -> service.save(account)));
	}

	@PutMapping
	public Mono<?> update(@RequestBody(required = true) Account account) {
		return service.findById(account.idAccount).flatMap(acc -> {
			if (acc.accountType.equals(account.accountType)) {
				return service.update(account);
			} else {
				return service.findByIdClientAndAccountType(account).flatMap(accc -> {
					return Mono.empty().switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST,
							"CUSTOMER WITH ID " + acc.idClient + " HAS A REGISTERED ACCOUNT OF THE SAME TYPE")));
				}).switchIfEmpty(Mono.defer(() -> service.save(account)));
			}
		}).switchIfEmpty(Mono.error(
				new ResponseStatusException(HttpStatus.NOT_FOUND, "ACCOUNT NOT FOUND ID: " + account.idAccount)));
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
