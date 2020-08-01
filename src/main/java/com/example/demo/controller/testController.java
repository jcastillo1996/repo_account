package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.impl.AccountServiceImpl;
import com.example.demo.webclient.ClientRestClient;
import com.example.demo.webclient.dto.ClientDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/testt")
public class testController {

	@Autowired
	AccountServiceImpl service;

	@Autowired
	ClientRestClient client;

	@GetMapping
	public Flux<ClientDTO> allClients() {
		return client.getAllClients();
	}

	@GetMapping("/{id}")
	public Mono<ClientDTO> client(@PathVariable(name = "id") Long id) {
		return client.getClientById(id);
	}
}
