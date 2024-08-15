package com.cadastro.usuarios.cadastrousuarios.service;

import com.cadastro.usuarios.cadastrousuarios.entities.endereco.EnderecoResponse;
import com.cadastro.usuarios.cadastrousuarios.entities.Usuarios;
import com.cadastro.usuarios.cadastrousuarios.exeptions.UsuarioJaCadastradoException;
import com.cadastro.usuarios.cadastrousuarios.repositories.EnderecoRepository;
import com.cadastro.usuarios.cadastrousuarios.repositories.UsuarioRepository;
import com.cadastro.usuarios.dtos.UsuariosFindDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cadastro.usuarios.cadastrousuarios.exeptions.DatabaseException;
import com.cadastro.usuarios.cadastrousuarios.exeptions.InvalidInputException;
import com.cadastro.usuarios.cadastrousuarios.exeptions.UsuarioNaoEncontradoException;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsuariosService {


    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    UsuarioRepository repository;

    public Usuarios criarUsuario(Usuarios usuario) {
        if (repository.findByCpf(usuario.getCpf()).isPresent()) {
            throw new UsuarioJaCadastradoException("CPF já está em uso");
        }
        return repository.save(usuario);
    }


    @Transactional
    public Optional<UsuariosFindDto> getById(Long id){
        try {
            if (id <= 0) {
                throw new InvalidInputException("ID inválido");
            }
            if (!repository.existsById(id)) {
                throw new UsuarioNaoEncontradoException("Usuario não encontrado");
            }
            ModelMapper mapper = new ModelMapper();

            return repository.findById(id).map(usuario -> mapper.map(usuario, UsuariosFindDto.class));
            

        } catch (UsuarioNaoEncontradoException | InvalidInputException ex) {
            log.error("Erro ao buscar usuario por ID: {}", id, ex);
            throw ex;
        } 
        catch (Exception ex) {
            log.error("Erro inesperado ao buscar usuario por ID: {}", id, ex);
            throw new DatabaseException("Erro no banco de dados");
        }
    }


    @Transactional
    public Optional<UsuariosFindDto> getByCPF(String cpf) {
        try {
            if (cpf == null || cpf.isEmpty()) {
                throw new InvalidInputException("Usuario inválido");
            }
            ModelMapper mapper = new ModelMapper();

            return repository.findByCpf(cpf).map(usuario -> mapper.map(usuario, UsuariosFindDto.class));

        } catch (UsuarioNaoEncontradoException | InvalidInputException ex) {
            log.error("Erro ao buscar usuario pelo CPF: {}", cpf, ex);
            throw ex;
        } catch (Exception ex) {
            log.error("Erro inesperado ao buscar usuario pelo CPF : {}", cpf, ex);
            throw new DatabaseException("Erro no banco de dados");
        }
    }

    @Transactional
    public void deleteById(Long id) {
        try {
            if (id <= 0) {
                throw new InvalidInputException("ID inválido");
            }
            if (!repository.existsById(id)) {
                throw new UsuarioNaoEncontradoException("Usuario não encontrado");
            }
            repository.deleteById(id);
        } catch (UsuarioNaoEncontradoException | InvalidInputException ex) {
            log.error("Erro ao deletar usuario por ID: {}", id, ex);
            throw ex;
        } catch (Exception ex) {
            log.error("Erro inesperado ao deletar usuario por ID: {}", id, ex);
            throw new DatabaseException("Erro no banco de dados");
        }
    }

    @Transactional
    public Optional<Usuarios> updateUsuarios(Long id, Usuarios updatedUsuarios){
        try {
            if (id <= 0) {
                throw new InvalidInputException("ID inválido");
            }
            if (!repository.existsById(id)) {
                throw new UsuarioNaoEncontradoException("Usuario não Encontrado");
            }
            updatedUsuarios.setId(id);

            return Optional.of(repository.save(updatedUsuarios));

        } catch (UsuarioNaoEncontradoException | InvalidInputException ex) {
            log.error("Erro ao buscar usuario por ID: {}", id, ex);
            throw ex;
        } catch (Exception ex) {
            log.error("Erro inesperado ao buscar usuario por ID: {}", id, ex);
            throw new DatabaseException("Erro no banco de dados");
        }
    }



    @Autowired
    private WebClient.Builder webClientBuilder;
    private static final String BRASILAPI_URL = "https://brasilapi.com.br/api/cep/v2/";

    public Mono<EnderecoResponse> buscarEnderecoPorCep(String cep) {
        WebClient webClient = webClientBuilder.build();
        return webClient.get()
                .uri(BRASILAPI_URL + cep)
                .retrieve()
                .bodyToMono(EnderecoResponse.class)
                .onErrorResume(e -> Mono.empty()); // Caso o CEP não seja encontrado
    }

}