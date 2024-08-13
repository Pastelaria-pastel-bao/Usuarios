package com.cadastro.usuarios.cadastrousuarios.service;

import com.cadastro.usuarios.cadastrousuarios.entities.Endereco;
import com.cadastro.usuarios.cadastrousuarios.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Endereco adicionarEndereco(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

}
