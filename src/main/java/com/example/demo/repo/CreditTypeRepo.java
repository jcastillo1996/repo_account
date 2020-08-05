package com.example.demo.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.demo.model.CreditType;

public interface CreditTypeRepo extends ReactiveMongoRepository<CreditType, Long>{

}
