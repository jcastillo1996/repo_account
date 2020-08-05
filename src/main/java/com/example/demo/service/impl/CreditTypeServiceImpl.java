package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.CreditType;
import com.example.demo.repo.CreditTypeRepo;
import com.example.demo.service.CreditTypeService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CreditTypeServiceImpl implements CreditTypeService {

	@Autowired
	CreditTypeRepo repo;
	
	@Override
	public Mono<CreditType> save(CreditType obj) {
		
		return repo.insert(obj);
	}

	@Override
	public Mono<CreditType> update(CreditType obj) {
		
		return repo.save(obj);
	}

	@Override
	public Flux<CreditType> findAll() {
		
		return repo.findAll();
	}

	@Override
	public Mono<CreditType> findById(Long v) {
		
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
