package com.exemplo.springbootinterview.cliente;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.exemplo.springbootinterview.cidade.Cidade;
import com.exemplo.springbootinterview.util.Sexo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	@Column(unique = true)
	private String email;
	
	@Enumerated(EnumType.STRING)
	private Sexo sexo;
	
	private LocalDate dataNascimento;
	
	private Integer idade;
	
	@OneToOne
	@NotNull
	private Cidade cidade;

}
