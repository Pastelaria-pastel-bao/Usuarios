package com.cadastro.usuarios.cadastrousuarios.repositories;

import com.cadastro.usuarios.cadastrousuarios.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import reactor.core.publisher.Mono;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}