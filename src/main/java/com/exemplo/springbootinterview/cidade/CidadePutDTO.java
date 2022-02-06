package com.exemplo.springbootinterview.cidade;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CidadePutDTO {

	@NotBlank(message = "Informe o id")
	private Long id;
	
	@NotBlank(message = "Informe o nome da cidade")
	private String nome;
	
	@NotNull
	private int codigoIbge;
}
