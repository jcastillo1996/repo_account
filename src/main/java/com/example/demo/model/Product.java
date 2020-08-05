package com.example.demo.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Document(collection = "products")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
public class Product {

	@Id
	private Long idProduct;
	private ProductType productType;
	private String productNumber;
	private String money;
	private AccountType accountType;
	private CreditType creditType;
	private LocalDate creationDate;
	private Double mount;
	private List<Long> idClient;
	private List<Long> idSignatory;

}
