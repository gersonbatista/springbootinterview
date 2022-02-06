package com.exemplo.springbootinterview;

import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.exemplo.springbootinterview.cidade.Cidade;
import com.exemplo.springbootinterview.cidade.CidadeController;
import com.exemplo.springbootinterview.cidade.CidadePostDTO;
import com.exemplo.springbootinterview.cidade.CidadePutDTO;
import com.exemplo.springbootinterview.cidade.CidadeService;

@SpringBootTest
class CidadeTest {

	@InjectMocks
	private CidadeController cidadeController;

	@Mock
	private CidadeService cidadeServiceMock;

	@BeforeEach
	void setUp() {

		BDDMockito.when(cidadeServiceMock.listar()).thenReturn(Arrays.asList(GeradorCidade.criaCidadeValida()));
		
		BDDMockito.when(cidadeServiceMock.findById(ArgumentMatchers.anyLong()))
				.thenReturn(GeradorCidade.criaCidadeValida());

		BDDMockito.when(cidadeServiceMock.buscarCidadePorNome(ArgumentMatchers.anyString()))
				.thenReturn(GeradorCidade.criaCidadeValida());

		BDDMockito.when(cidadeServiceMock.buscarCidadePorEstado(ArgumentMatchers.anyInt()))
				.thenReturn(Arrays.asList(GeradorCidade.criaCidadeValida()));

		BDDMockito.when(cidadeServiceMock.salvar(ArgumentMatchers.any(CidadePostDTO.class)))
				.thenReturn(GeradorCidade.criaCidadeValida());

		BDDMockito.doNothing().when(cidadeServiceMock).alterar(ArgumentMatchers.any(CidadePutDTO.class));

		BDDMockito.doNothing().when(cidadeServiceMock).deletar(ArgumentMatchers.anyLong());
	}

	@Test
	@DisplayName("retorna uma lista de cidades quando sucesso")
	void listaTodas_Cidade_QuandoSucesso() {
		String expectedName = GeradorCidade.criaCidadeValida().getNome();

		List<Cidade> cidades = cidadeController.listar().getBody();

		Assertions.assertThat(cidades).isNotNull().isNotEmpty().hasSize(1);

		Assertions.assertThat(cidades.get(0).getNome()).isEqualTo(expectedName);
	}

	@Test
	@DisplayName("buscaPorId return Cidade quando sucesso")
	void buscaPorId_RetornaCidade_QuandoSucesso() {
		Long expectedId = GeradorCidade.criaCidadeValida().getId();

		Cidade cidade = cidadeController.buscarCidadeId(1L).getBody();

		Assertions.assertThat(cidade).isNotNull();

		Assertions.assertThat(cidade.getId()).isNotNull().isEqualTo(expectedId);
	}

	@Test
	@DisplayName("buscaPorNome return Cidade quando sucesso")
	void buscaPorNome_RetornaCidade_QuandoSucessol() {
		String expectedName = GeradorCidade.criaCidadeValida().getNome();

		Cidade cidade = cidadeController.buscarCidadePorNome("Eirunepé").getBody();

		Assertions.assertThat(cidade).isNotNull();

		Assertions.assertThat(cidade.getNome()).isEqualTo(expectedName);
	}

	@Test
	@DisplayName("salva cidade quando sucesso")
	void salva_Cidade_QuandoSucesso() {

		Cidade cidade = cidadeController.salvar(CidadePost.criaPostDTO()).getBody();
		Assertions.assertThat(cidade).isNotNull().isEqualTo(GeradorCidade.criaCidadeValida());
	}

	@Test
	@DisplayName("remove cidade quando sucesso")
	void atualiza_Cidade_QuandoSucesso() {

		Assertions.assertThatCode(() -> cidadeController.alterar(CidadePut.criaPutDTO()))
				.doesNotThrowAnyException();

		ResponseEntity<Void> entity = cidadeController.alterar(CidadePut.criaPutDTO());

		Assertions.assertThat(entity).isNotNull();

		Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
	}

	@Test
	@DisplayName("remove cidade quando sucesso")
	void deleta_Cidade_QuandoSucesso() {

		Assertions.assertThatCode(() -> cidadeController.deletar(1)).doesNotThrowAnyException();

		ResponseEntity<Void> entity = cidadeController.deletar(1);

		Assertions.assertThat(entity).isNotNull();

		Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
	}

}
