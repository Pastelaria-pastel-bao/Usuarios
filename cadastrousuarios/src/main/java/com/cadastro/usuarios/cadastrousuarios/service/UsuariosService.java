package com.cadastro.usuarios.cadastrousuarios.service;

import com.cadastro.usuarios.cadastrousuarios.entities.Usuarios;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsuariosService {


    @Transactional
    public Usuarios criar(Usuarios usuario) {
        try {
            if (UsuarioRepository.findByNome(usuario.getNome()).isPresent()) {
                throw new UsuarioDuplicadoException("Usuario com este nome já existe");
            }
            return UsuarioRepository.save(usuario);
        } catch (UsuarioDuplicadoException | InvalidInputException ex) {
            log.error("Erro ao criar Usuario: {}", usuario, ex);
            throw ex;
            
        } catch (Exception ex) {
            log.error("Erro inesperado ao criar usuario: {}", usuario, ex);
            throw new DatabaseException("Erro no banco de dados");
        }
    }


    @Transactional
    public Optional<Usuarios> getByCPF(String cpf) {
        try {
            if (cpf == null || cpf.isEmpty()) {
                throw new InvalidInputException("Usuario inválido");
            }
            return Optional.ofNullable(UsuarioRepository.findByCpf(cpf)
                    .orElseThrow(() -> new UsuariolNaoEncontradoException("Usuario não encontrado")));
        } catch (UsuariolNaoEncontradoException | InvalidInputException ex) {
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
            if (!UsuarioRepository.existsById(id)) {
                throw new PastelNaoEncontradoException("Usuario não encontrado");
            }
            UsuarioRepository.deleteById(id);
        } catch (UsuarioNaoEncontradoException | InvalidInputException ex) {
            log.error("Erro ao deletar usuario por ID: {}", id, ex);
            throw ex;
        } catch (Exception ex) {
            log.error("Erro inesperado ao deletar usuario por ID: {}", id, ex);
            throw new DatabaseException("Erro no banco de dados");
        }
    }

}