package com.example.demo.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Document(collection = "accounts")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {
	@Id
	public Long idAccount;
	public String accountNumber;
	public String money;
	public String accountType;
	public  LocalDate creationDate;
	public Double mount;
	public Long idClient;
	

}
