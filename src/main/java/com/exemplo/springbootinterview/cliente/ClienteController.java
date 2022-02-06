package com.exemplo.springbootinterview.cliente;

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
@RequestMapping("api/v1/cliente")
@Tag(name = "Clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping
	public ResponseEntity<List<Cliente>> listar() {
		return ResponseEntity.status(HttpStatus.OK).body(clienteService.listAll());
	}
	
	@GetMapping("id/{id}")
	public ResponseEntity<Cliente> buscarCidadeId(@PathVariable("id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(clienteService.buscarClientePorId(id));
	}

	@GetMapping("nome/{nome}")
	public ResponseEntity<Cliente> buscarCidadePorNome(@PathVariable("nome") String nome) {
		return ResponseEntity.status(HttpStatus.OK).body(clienteService.buscarClientPorNome(nome));
	}

	@PostMapping
	public ResponseEntity<Cliente> salvar(@Valid @RequestBody ClientePostDTO dto) {
		return new ResponseEntity<>(clienteService.salvar(dto), HttpStatus.CREATED);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> deletar(@PathVariable long id) {
		clienteService.deletar(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PutMapping
	public ResponseEntity<Void> replace(@RequestBody ClientePutDTO dto) {
		clienteService.alterar(dto);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}