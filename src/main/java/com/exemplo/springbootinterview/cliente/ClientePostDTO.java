package com.exemplo.springbootinterview.cliente;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ClientePostDTO {

	@NotBlank(message = "Informe o nome")
	private String nome;
	
	@Email(message = "Informe um email válido")
	private String email;
	
	@NotBlank(message = "Informe o sexo")
	private String sexo;
	
	@NotNull(message = "Informe a data de nascimento")
	private String dataNascimento;
	
	private Integer idade;
	
	@NotNull
	private Long cidadeID;

}
