package com.cadastro.usuarios.cadastrousuarios.entities.repository;

import com.cadastro.usuarios.cadastrousuarios.entities.endereco.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstadoRepository extends JpaRepository<Estado, Integer> {
    Optional<Estado> findByNome(String nome);
}



