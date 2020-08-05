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

import com.example.demo.model.ProductType;
import com.example.demo.service.impl.ProductTypeServiceImpl;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api_product_type")
public class ProductTypeController {

	@Autowired
	ProductTypeServiceImpl service;

	@GetMapping
	public Flux<ProductType> findAll() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public Mono<ProductType> findById(@PathVariable(name = "id") Long id) {
		return service.findById(id).switchIfEmpty(
				Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "PRODUCT TYPE NOT FOUND ID: " + id)));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<ProductType> save(@RequestBody ProductType type) {

		return service.save(type);
	}

	@PutMapping
	public Mono<ProductType> update(@RequestBody(required = true) ProductType type) {

		return service.update(type);
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

