package com.example.demo.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Document(collection = "type_credit")
@Data
@Builder
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CreditType {

	@Id
	@NotNull(message = "SHOULD NOT BE NULL")
	private Long idType;
	@NotNull(message = "SHOULD NOT BE NULL")
	@NotEmpty(message = "ENTER TYPE NAME")
	private String nameType;

}
