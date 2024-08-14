package com.cadastro.usuarios.cadastrousuarios.service;

import com.cadastro.usuarios.cadastrousuarios.entities.Endereco;
import com.cadastro.usuarios.cadastrousuarios.entities.Usuarios;
import com.cadastro.usuarios.cadastrousuarios.repositories.EnderecoRepository;
import com.cadastro.usuarios.cadastrousuarios.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Endereco adicionarEndereco(Endereco endereco) {
        // Verificar se o ID do usuário está presente no endereço
        Long usuarioId = endereco.getUsuarioId();
        if (usuarioId == null) {
            throw new RuntimeException("ID do usuário não pode ser nulo");
        }

        // Verificar se o usuário existe
        Optional<Usuarios> usuarioOptional = usuarioRepository.findById(usuarioId);

        if (usuarioOptional.isPresent()) {
            Usuarios usuario = usuarioOptional.get();



            // Salvar o endereço
            Endereco enderecoSalvo = enderecoRepository.save(endereco);

            // Associar o endereço salvo ao usuário e atualizar o usuário
            usuario.setEndereco(enderecoSalvo);
            usuarioRepository.save(usuario);

            return enderecoSalvo;
        } else {
            throw new RuntimeException("Usuário não encontrado com o ID: " + usuarioId);
        }
    }

}
