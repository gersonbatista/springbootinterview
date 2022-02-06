package com.exemplo.springbootinterview.cliente;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exemplo.springbootinterview.cidade.Cidade;
import com.exemplo.springbootinterview.exception.BadRequestException;
import com.exemplo.springbootinterview.util.Sexo;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public List<Cliente> listAll() {
		return clienteRepository.findAll();
	}

	public Cliente buscarClientePorId(Long id) {
		return clienteRepository.findById(id).orElseThrow(() -> new BadRequestException("Cliente not found"));
	}

	public Cliente buscarClientPorNome(String nome) {
		return clienteRepository.findByNomeIgnoreCase(nome)
				.orElseThrow(() -> new BadRequestException("Cliente not found"));
	}

	public Cliente salvar(@Valid ClientePostDTO dto) {
		Cliente cliente = new Cliente();
		BeanUtils.copyProperties(dto, cliente);
		cliente.setSexo(Sexo.valueOf(dto.getSexo()));
		cliente.setDataNascimento(LocalDate.parse(dto.getDataNascimento(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		cliente.setCidade(new Cidade(dto.getCidadeID()));
		return clienteRepository.save(cliente);
	}
	
	public void deletar(Long id) {
		verificaSeExisteCliente(id);
		clienteRepository.deleteById(id);
	}

	public void alterar(ClientePutDTO dto) {
		Cliente clienteSalvo = buscarClientePorId(dto.getId());
		BeanUtils.copyProperties(dto, clienteSalvo);
		clienteRepository.save(clienteSalvo);

	}

	private void verificaSeExisteCliente(Long id) {
		if (!clienteRepository.existsById(id)) {
			throw new BadRequestException("Cliente not found");
		}
	}
}