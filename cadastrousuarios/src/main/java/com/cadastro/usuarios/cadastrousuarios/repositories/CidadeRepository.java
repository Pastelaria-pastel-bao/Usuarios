package com.cadastro.usuarios.cadastrousuarios.repositories;

import com.cadastro.usuarios.cadastrousuarios.entities.endereco.Cidade;
import com.cadastro.usuarios.cadastrousuarios.entities.endereco.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CidadeRepository extends JpaRepository<Cidade, Integer> {
    Optional<Cidade> findByNomeAndEstado(String nome, Estado estado);
}