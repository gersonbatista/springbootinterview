package com.exemplo.springbootinterview.cidade;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exemplo.springbootinterview.util.Estado;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

	Optional<Cidade> findByNomeOrderByNomeAsc(String nome);
	List<Cidade> findByEstadoOrderByNomeAsc(Estado estado);

}
