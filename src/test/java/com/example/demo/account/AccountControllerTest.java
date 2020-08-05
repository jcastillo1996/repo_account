package com.example.demo.account;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.example.demo.controller.ProductController;
import com.example.demo.model.AccountType;
import com.example.demo.model.Product;
import com.example.demo.model.ProductType;
import com.example.demo.service.impl.ProductServiceImpl;

import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = ProductController.class)
class AccountControllerTest {

	@MockBean
	ProductServiceImpl service;

	@Autowired
	WebTestClient webClient;
	
	
	@Test
	void findById() {
		List<Long> lds=new ArrayList<>();
		lds.add(2L);
		Product prod= new Product();
		prod.setIdProduct(3L);
		prod.setProductType(new ProductType(1L,"CUENTA"));
		prod.setProductNumber("0009994384383");
		prod.setMoney("S");
		prod.setAccountType(new AccountType(3L,"PLAZO FIJO"));
		prod.setCreditType(null);
		prod.setCreationDate(LocalDate.of(2020, Month.JULY, 4));
		prod.setMount(1500.98);
		prod.setIdClient(lds);
		prod.setIdSignatory(null);	
		Mono<Product> prodMono = Mono.just(prod);
		when(service.findById(prod.getIdProduct())).thenReturn(prodMono);
		webClient.get().uri("/api_product/{id}",prod.getIdProduct()).accept(MediaType.APPLICATION_JSON).exchange().expectStatus().isOk()
				.expectBody(Product.class).value(acc1 -> prod.getIdProduct(),equalTo(3L));
	}
	
}
