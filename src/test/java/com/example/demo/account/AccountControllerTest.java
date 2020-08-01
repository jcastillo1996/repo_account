package com.example.demo.account;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.example.demo.controller.AccountController;
import com.example.demo.model.Account;
import com.example.demo.model.AccountType;
import com.example.demo.service.impl.AccountServiceImpl;

import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = AccountController.class)
class AccountControllerTest {

	@MockBean
	AccountServiceImpl service;

	@Autowired
	private WebTestClient webClient;
	
	
	@Test
	void findById() {
		Long id = 4L;
		Account acc= new Account();
		acc.idAccount=id;	
		Mono<Account> accountMono = Mono.just(acc);
		when(service.findById(4L)).thenReturn(accountMono);
		webClient.get().uri("/api_account/4").accept(MediaType.APPLICATION_JSON).exchange().expectStatus().isOk()
				.expectBody(Account.class).value(acc1 -> acc.idAccount,equalTo(4L));
	}
	@Test
	void save() {
		Long id = 9L;
		Account acc= new Account();
		AccountType type=new  AccountType();
		type.idTypeAccount=1L;
		type.typeAccount="AHORRO";
		
		acc.idAccount=id;
		acc.accountNumber="9728357482294";
		acc.money="S";
		acc.accountType=type;
		acc.creationDate=LocalDate.now();
		acc.mount=54893.90;
		acc.idClient=8L;
		Mono<Account> accMono = Mono.just(acc);
		when(service.save(acc)).thenReturn(accMono);
		webClient.post().uri("/api_account").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.body(Mono.just(acc), Account.class).exchange().expectStatus().isCreated();
	}
	@Test
	void update() {
		Long id = 9L;
		Account acc= new Account();
		AccountType type=new  AccountType();
		type.idTypeAccount=1L;
		type.typeAccount="AHORRO";
		
		acc.idAccount=id;
		acc.accountNumber="9728357482294";
		acc.money="S";
		acc.accountType=type;
		acc.creationDate=LocalDate.now();
		acc.mount=54893.90;
		acc.idClient=8L;
		Mono<Account> accMono = Mono.just(acc);
		when(service.update(acc)).thenReturn(accMono);
		webClient.put().uri("/api_account").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.body(Mono.just(acc), Account.class).exchange().expectStatus().isOk();
	}
	@Test
	void deleteById() {
		Long id = 2L;
		when(service.deleteById(id)).thenReturn(Mono.empty());
		webClient.delete()
		.uri("/api_account/2656565")
		.exchange()
		.expectStatus().isOk();
	}
}
