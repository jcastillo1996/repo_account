package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.example.demo.webclient.ClientRestClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api_account")
public class AccountController {

	@Autowired
	AccountServiceImpl service;

	@Autowired
	ClientRestClient webClient;

	@Value("${wclient.urls.typeclient}")
	private String clientType;

	@GetMapping
	public Flux<Account> findAll() {
		System.out.println(clientType);
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
		return webClient.getClientById(account.idClient).flatMap(dto -> {
			if (dto.typeClient.typeName.equals(clientType)) {// Cliente Personal
				return service.findByIdClientAndAccountType(account).flatMap(acc -> {
					return Mono.empty().switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST,
							"CUSTOMER WITH ID " + acc.idClient + " HAS A REGISTERED ACCOUNT OF THE SAME TYPE")));
				}).switchIfEmpty(Mono.defer(() -> service.save(account)));
			} else {
				// Cliente Empresarial
				if (account.accountType.typeAccount.equals("AHORRO")
						|| account.accountType.typeAccount.equals("PLAZO FIJO")) {
					return Mono.empty()
							.switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST,
									 "EMPRESARIAL CUSTOMER, MUST NOT HAVE A " + account.accountType.typeAccount + " ACCOUNT")));
				} else {
					return service.save(account);
				}
			}
		});

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
