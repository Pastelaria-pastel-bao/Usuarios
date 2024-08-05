package com.cadastro.usuarios.cadastrousuarios.service;

import com.cadastro.usuarios.cadastrousuarios.entities.Usuarios;
import com.cadastro.usuarios.cadastrousuarios.repositories.UsuarioRepository;
import com.cadastro.usuarios.dtos.UsuariosFindDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cadastro.usuarios.cadastrousuarios.exeptions.DatabaseException;
import com.cadastro.usuarios.cadastrousuarios.exeptions.InvalidInputException;
import com.cadastro.usuarios.cadastrousuarios.exeptions.UsuarioNaoEncontradoException;


import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsuariosService {

    private UsuarioRepository repository;

    @Transactional
    public Usuarios criar(Usuarios usuario) {
        try {

            return repository.save(usuario);
        
        } catch (Exception ex) {
            log.error("Erro inesperado ao criar usuario: {}", usuario, ex);
            throw new DatabaseException("Erro no banco de dados");
        }
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

            Usuarios usuario = repository.findById(id).get();

            UsuariosFindDto dto = mapper.map(usuario, UsuariosFindDto.class);

            return Optional.of(dto);

        } catch (UsuarioNaoEncontradoException | InvalidInputException ex) {
            log.error("Erro ao buscar usuario por ID: {}", id, ex);
            throw ex;
        } catch (Exception ex) {
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

            Usuarios usuario = repository.findByCpf(cpf).get();

            UsuariosFindDto dto = mapper.map(usuario, UsuariosFindDto.class);

            return Optional.of(dto);

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
    public Optional<Usuarios> updateUsuarios(Long id, Usuarios updatedPasteis){
        try {
            if (id <= 0) {
                throw new InvalidInputException("ID inválido");
            }
            if (!repository.existsById(id)) {
                throw new UsuarioNaoEncontradoException("Usuario não enAcontrado");
            }
            updatedPasteis.setId(id);

            return Optional.of(repository.save(updatedPasteis));

        } catch (UsuarioNaoEncontradoException | InvalidInputException ex) {
            log.error("Erro ao buscar usuario por ID: {}", id, ex);
            throw ex;
        } catch (Exception ex) {
            log.error("Erro inesperado ao buscar usuario por ID: {}", id, ex);
            throw new DatabaseException("Erro no banco de dados");
        }
    }

}