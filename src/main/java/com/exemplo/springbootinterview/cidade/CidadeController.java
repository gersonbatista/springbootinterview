package com.exemplo.springbootinterview.cidade;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/v1/cidade")
@Tag(name = "Cidades")
public class CidadeController {

	@Autowired
	private CidadeService cidadeService;

	@GetMapping
	public ResponseEntity<List<Cidade>> listar() {
		return ResponseEntity.status(HttpStatus.OK).body(cidadeService.listar());
	}
	
	@GetMapping("id/{id}")
	public ResponseEntity<Cidade> buscarCidadeId(@PathVariable("id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(cidadeService.findById(id));
	}

	@GetMapping("nome/{nome}")
	public ResponseEntity<Cidade> buscarCidadePorNome(@PathVariable("nome") String nome) {
		return ResponseEntity.status(HttpStatus.OK).body(cidadeService.buscarCidadePorNome(nome));
	}

	@GetMapping(value = "cogidoIbge/{cogidoIbge}")
	public ResponseEntity<List<Cidade>> buscarCidadePorEstado(@PathVariable("cogidoIbge") int cogidoIbge) {
		return ResponseEntity.status(HttpStatus.OK).body(cidadeService.buscarCidadePorEstado(cogidoIbge));
	}

	@PostMapping
	public ResponseEntity<Cidade> salvar(@Valid @RequestBody CidadePostDTO dto) {
		return new ResponseEntity<>(cidadeService.salvar(dto), HttpStatus.CREATED);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> deletar(@PathVariable long id) {
		cidadeService.deletar(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PutMapping
	public ResponseEntity<Void> alterar(@RequestBody CidadePutDTO dto) {
		cidadeService.alterar(dto);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
