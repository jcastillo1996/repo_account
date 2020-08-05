package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ProductType;
import com.example.demo.repo.ProductTypeRepo;
import com.example.demo.service.ProductTypeService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductTypeServiceImpl implements ProductTypeService{

	@Autowired
	ProductTypeRepo repo;
	
	@Override
	public Mono<ProductType> save(ProductType obj) {
		
		return repo.insert(obj);
	}

	@Override
	public Mono<ProductType> update(ProductType obj) {
		
		return repo.save(obj);
	}

	@Override
	public Flux<ProductType> findAll() {
		
		return repo.findAll();
	}

	@Override
	public Mono<ProductType> findById(Long v) {
		
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
