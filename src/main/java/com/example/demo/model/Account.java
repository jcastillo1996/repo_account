package com.example.demo.model;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Document(collection = "accounts")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Account {
	@Id
	public Long idAccount;
	@NotNull(message = "NO DEBE SER NULO")
	@NotEmpty(message = "NO DEBE ESTAR VACIO")
	public String accountNumber;
	public String money;
	public AccountType accountType;
	public  LocalDate creationDate;
	public Double mount;
	public List<Long> idClient;
	public List<Long> idSignatory;
	

}
