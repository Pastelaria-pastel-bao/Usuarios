package com.cadastro.usuarios.cadastrousuarios.repositories;

import com.cadastro.usuarios.cadastrousuarios.entities.endereco.Bairro;
import com.cadastro.usuarios.cadastrousuarios.entities.endereco.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BairroRepository extends JpaRepository<Bairro, Integer> {
    Optional<Bairro> findByNomeAndCidade(String nome, Cidade cidade);
}
