package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Product;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService extends ICRUD<Product, Long> {

	Flux<Product> findByIdClient(List<Long> id);

	Mono<Product> findByIdClientAndAccountType(Product product);
}
