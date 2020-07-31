package com.example.demo.webclient.dto;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ClientDTO implements Serializable {
	private static final long serialVersionUID = -7002717789008112626L;
	
	public Long idClient;
	
	public String document;

	public String firstName;

	public String secondName;

	public String lastName1;

	public String lastName2;

	public LocalDate birthDate;

	public TypeClientDTO typeClient;

}
