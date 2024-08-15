package com.cadastro.usuarios.cadastrousuarios.repositories;

import com.cadastro.usuarios.cadastrousuarios.entities.endereco.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}