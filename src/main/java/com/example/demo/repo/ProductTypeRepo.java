package com.example.demo.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.demo.model.ProductType;

public interface ProductTypeRepo extends ReactiveMongoRepository<ProductType, Long> {

}
