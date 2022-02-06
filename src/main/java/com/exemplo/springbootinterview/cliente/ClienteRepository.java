package com.exemplo.springbootinterview.cliente;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	 Optional<Cliente> findByNomeIgnoreCase(String nome);

}