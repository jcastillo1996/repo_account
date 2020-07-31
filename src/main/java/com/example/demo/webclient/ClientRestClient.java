package com.example.demo.webclient;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.webclient.dto.ClientDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ClientRestClient {


	private final WebClient webclient;
	
	public ClientRestClient(WebClient.Builder webclientBuilder, @Value("${wclient.urls.client}")String urlBase) {
		this.webclient=webclientBuilder.baseUrl(urlBase).build();
	}

	public List<ClientDTO> getAllClients() {
		return webclient
				.get()
				.uri("/3")
				.retrieve()
				.bodyToFlux(ClientDTO.class)
				.collectList()
				.block();
	}

}
