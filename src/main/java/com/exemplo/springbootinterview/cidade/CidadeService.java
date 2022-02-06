package com.exemplo.springbootinterview.cidade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exemplo.springbootinterview.exception.BadRequestException;
import com.exemplo.springbootinterview.util.Estado;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;

	public List<Cidade> listar() {
		return cidadeRepository.findAll();
	}

	public Cidade findById(Long id) {
		return cidadeRepository.findById(id).orElseThrow(() -> new BadRequestException("Cidade not found"));
	}

	public Cidade buscarCidadePorNome(String nome) {
		return cidadeRepository.findByNomeOrderByNomeAsc(nome).orElseThrow(() -> new BadRequestException("Cidade not found"));
	}

	public List<Cidade> buscarCidadePorEstado(int codigoIbge) {
		verificaSeExisteEstado(codigoIbge);
		return cidadeRepository.findByEstadoOrderByNomeAsc(Estado.fromCodigoIbge(codigoIbge));
	}

	public Cidade salvar(CidadePostDTO dto) {
		verificaSeExisteEstado(dto.getCodigoIbge());
		Cidade cidade = Cidade.builder().nome(dto.getNome()).estado(Estado.fromCodigoIbge(dto.getCodigoIbge())).build();
		return cidadeRepository.save(cidade);
	}

	public void deletar(Long id) {
		verificaSeExisteCidade(id);
		cidadeRepository.deleteById(id);
	}

	public void alterar(CidadePutDTO dto) {
		Cidade cidadeSalva = findById(dto.getId());
		Cidade cidade = Cidade.builder().id(dto.getId()).nome(dto.getNome()).estado(Estado.fromCodigoIbge(dto.getCodigoIbge())).build();
		cidade.setId(cidadeSalva.getId());
		cidadeRepository.save(cidade);

	}
	
	private void verificaSeExisteCidade(Long id) {
		if (!cidadeRepository.existsById(id)) {
			throw new BadRequestException("Cliente not found");
		}
	}
	
	private void verificaSeExisteEstado(int codigoIbge) {
		if (Estado.fromCodigoIbge(codigoIbge) == null) {
			throw new BadRequestException("Estado not found");
		}
	}

}
