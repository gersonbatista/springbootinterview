package com.exemplo.springbootinterview.cliente;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ClientePutDTO {

	@NotBlank(message = "Informe o id")
	private Long id;
	
	@NotBlank(message = "Informe o nome da cidade")
	private String nome;
}
