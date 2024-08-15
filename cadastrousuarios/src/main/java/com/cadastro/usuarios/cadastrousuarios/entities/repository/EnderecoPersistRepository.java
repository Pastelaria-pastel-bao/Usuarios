package com.cadastro.usuarios.cadastrousuarios.entities.repository;

import com.cadastro.usuarios.cadastrousuarios.entities.endereco.EnderecoPersist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoPersistRepository extends JpaRepository<EnderecoPersist, Long> {

}