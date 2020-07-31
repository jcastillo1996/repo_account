package com.example.demo.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.demo.model.AccountType;

public interface AccountTypeRepo extends ReactiveMongoRepository<AccountType, Long> {

}
