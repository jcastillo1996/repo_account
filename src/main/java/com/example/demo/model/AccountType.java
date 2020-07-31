package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "type_account")
public class AccountType {
	@Id
	private Long idTypeAccount;
	private String typeAccount;
	public Long getIdTypeAccount() {
		return idTypeAccount;
	}
	public void setIdTypeAccount(Long idTypeAccount) {
		this.idTypeAccount = idTypeAccount;
	}
	public String getTypeAccount() {
		return typeAccount;
	}
	public void setTypeAccount(String typeAccount) {
		this.typeAccount = typeAccount;
	}
	@Override
	public String toString() {
		return "AccountType [idTypeAccount=" + idTypeAccount + ", typeAccount=" + typeAccount + "]";
	}
	public AccountType(Long idTypeAccount, String typeAccount) {
		this.idTypeAccount = idTypeAccount;
		this.typeAccount = typeAccount;
	}
	
	
	
}
