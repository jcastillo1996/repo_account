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

import com.example.demo.model.Product;
import com.example.demo.service.impl.ProductServiceImpl;
import com.example.demo.webclient.ClientRestClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api_product")
public class ProductController {

	@Autowired
	ProductServiceImpl service;
	
	//@Autowired
	//ClientRestClient webClient;

	@GetMapping
	public Flux<Product> findAll() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public Mono<Product> findById(@PathVariable(name = "id") Long id) {
		return service.findById(id).switchIfEmpty(
				Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "PRODUCT NOT FOUND ID: " + id)));
	}
	/*
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<?> save(@RequestBody Product product) {

		return webClient.getClientById(product.getIdClient().get(0)).flatMap(dto -> {
			
			if (dto.typeClient.typeName.equals("PERSONAL")) {// Cliente Personal
				
				return service.findByIdClientAndAccountType(product)
						.flatMap(acc -> {
							return Mono.empty()
									.switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST,"CUSTOMER WITH ID " + acc.getIdClient() + " HAS A REGISTERED ACCOUNT OF THE SAME TYPE")));
						})
						.switchIfEmpty(Mono.defer(() -> service.save(product)));
			} else {
				// Cliente EMPRESARIAL
				String accountType=product.getAccountType().getTypeAccount();
				if (accountType.equals("AHORRO")|| accountType.equals("PLAZO FIJO")) {
					
					return Mono.empty()
							.switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST,"EMPRESARIAL CUSTOMER, MUST NOT HAVE A " + product.getAccountType().getTypeAccount() + " ACCOUNT")));
				} else {
					return service.save(product);
				}
			}
		});
	}

	@PutMapping
	public Mono<?> update(@RequestBody(required = true) Product product) {

	return service.findById(product.getIdProduct()).flatMap(prod->{
		if (prod.equals(product)) {
			return service.update(product);
		}
		return webClient.getClientById(product.getIdClient().get(0)).flatMap(dto -> {
			
			if (dto.typeClient.typeName.equals("PERSONAL")) {// Cliente Personal
				
				return service.findByIdClientAndAccountType(product)
						.flatMap(acc -> {
							return Mono.empty()
									.switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST,"CUSTOMER WITH ID " + acc.getIdClient() + " HAS A REGISTERED ACCOUNT OF THE SAME TYPE")));
						})
						.switchIfEmpty(Mono.defer(() -> service.update(product)));
			} else {
				// Cliente EMPRESARIAL
				String accountType=product.getAccountType().getTypeAccount();
				if (accountType.equals("AHORRO")|| accountType.equals("PLAZO FIJO")) {
					
					return Mono.empty()
							.switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST,"EMPRESARIAL CUSTOMER, MUST NOT HAVE A " + product.getAccountType().getTypeAccount() + " ACCOUNT")));
				} else {
					return service.update(product);
				}
			}
		});
	}).switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "ACCOUNT NOT FOUND ID: " + product.getIdProduct())));
	}
*/
	@DeleteMapping("/{id}")
	public Mono<Void> deleteById(@PathVariable Long id) {

		return service.deleteById(id);
	}

	@DeleteMapping("/deleteAll")
	public Mono<Void> deleteAll() {

		return service.deleteAll();
	}

}

