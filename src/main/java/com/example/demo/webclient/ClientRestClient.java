package com.example.demo.webclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.webclient.dto.ClientDTO;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class ClientRestClient {

	private final WebClient webclient;

	public ClientRestClient(WebClient.Builder webclientBuilder, @Value("${wclient.urls.client}") String baseURl) {
		this.webclient = webclientBuilder.baseUrl(baseURl).build();
	}

	public Flux<ClientDTO> getAllClients() {
		return webclient.get().uri("/").retrieve().bodyToFlux(ClientDTO.class);
	}

	public Mono<ClientDTO> getClientById(Long id) {
		return webclient.get().uri("/{id}", id).retrieve().onStatus(HttpStatus::is4xxClientError, response -> {
			return Mono
					.error(new ResponseStatusException(response.statusCode(), response.statusCode().getReasonPhrase()));
		}).onStatus(HttpStatus::is5xxServerError, response -> {
			return Mono
					.error(new ResponseStatusException(response.statusCode(), response.statusCode().getReasonPhrase()));
		}).bodyToMono(ClientDTO.class);

	}

}
