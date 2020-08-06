package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Product;
import com.example.demo.repo.ProductRepo;
import com.example.demo.service.ProductService;
import com.example.demo.webclient.ClientRestClient;
import com.example.demo.webclient.dto.ClientDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepo repo;
	@Autowired
	ClientRestClient webClient;
	
	@Override
	public Mono<Product> save(Product obj) {
		
		return repo.insert(obj);
	}

	@Override
	public Mono<Product> update(Product obj) {
		
		return repo.save(obj);
	}

	@Override
	public Flux<Product> findAll() {
		
		return repo.findAll();
	}

	@Override
	public Mono<Product> findById(Long v) {
		
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
	public Flux<Product> findByIdClient(List<Long> id) {
		
		return repo.findByIdClient(id);
	}

	@Override
	public Mono<Product> findByIdClientAndAccountType(Product product) {
		
		return repo.findByIdClientAndAccountType(product.getIdClient(), product.getAccountType());
	}
	
	/*
	 * Para WebClient
	 * */
	public Mono<ClientDTO> getClientById(Long id){
		return webClient.getClientById(id);
	}
}
