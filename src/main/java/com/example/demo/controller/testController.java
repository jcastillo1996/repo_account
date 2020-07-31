package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.impl.AccountServiceImpl;
import com.example.demo.webclient.ClientRestClient;
import com.example.demo.webclient.dto.ClientDTO;

@RestController
@RequestMapping("/testt")
public class testController {

	@Autowired
	AccountServiceImpl service;
	
	@Autowired
	ClientRestClient client;
	
	@GetMapping
	public List<ClientDTO> test() {
		return client.getAllClients();
	}

}
