package com.example.demo.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.demo.model.Account;

public interface IAccountRepo extends ReactiveMongoRepository<Account, Long> {

}
