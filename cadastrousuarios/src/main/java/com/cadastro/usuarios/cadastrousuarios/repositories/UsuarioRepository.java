package com.cadastro.usuarios.cadastrousuarios.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cadastro.usuarios.cadastrousuarios.entities.Usuarios;


public interface UsuarioRepository extends JpaRepository<Usuarios, Long>{

    Optional<Usuarios> findByCpf(String cpf);
    Boolean existsByEmail(String email);
    
}
