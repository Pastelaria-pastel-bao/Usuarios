package com.cadastro.usuarios.cadastrousuarios.service;

import static org.mockito.Mockito.when;

import java.util.Optional;

import static com.cadastro.usuarios.cadastrousuarios.communs.UsuariosConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.cadastro.usuarios.cadastrousuarios.entities.Usuarios;
import com.cadastro.usuarios.cadastrousuarios.exeptions.DatabaseException;
import com.cadastro.usuarios.cadastrousuarios.exeptions.InvalidInputException;
import com.cadastro.usuarios.cadastrousuarios.exeptions.UsuarioNaoEncontradoException;
import com.cadastro.usuarios.cadastrousuarios.repositories.UsuarioRepository;
import com.cadastro.usuarios.dtos.UsuariosFindDto;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @InjectMocks
    private UsuariosService service;

    @Mock
    private UsuarioRepository repository;

    @Test
    public void criarUsuario_ComDadosValidos_retornarUsuario(){
        when(service.criarUsuario(usuarioCriar)).thenReturn(usuarioPadrao);

       Usuarios teste = service.criarUsuario(usuarioCriar);

       assertThat(teste).isEqualTo(usuarioPadrao);
    }

    @Test
    public void criarUsuario_ComDadosInvalidos_lancarException(){
        when(service.criarUsuario(usuarioNulo)).thenThrow(new DatabaseException("Dados Invalidos"));

        assertThatThrownBy(() -> service.criarUsuario(usuarioNulo)).isInstanceOf(DatabaseException.class);

        when(service.criarUsuario(usuarioVazio)).thenThrow(new DatabaseException("Dados Invalidos"));

        assertThatThrownBy(() -> service.criarUsuario(usuarioVazio)).isInstanceOf(DatabaseException.class);
    }

    @Test
    public void buscarUsuarioPeloId_ComIdExistente_retornarUsuario(){

        when(repository.existsById(1L)).thenReturn(true);
        when(repository.findById(1L)).thenReturn(Optional.of(usuarioPadrao));

        Optional<UsuariosFindDto> dto = service.getById(1L);

        assertThat(dto.get().getId()).isEqualTo(1L);
        assertThat(dto.get().getNome()).isEqualTo("Murilo");
        assertThat(dto.get().getEmail()).isEqualTo("teste@gmail.com");
        assertThat(dto.get().getTelefone()).isEqualTo("42999999999");

    }

    @Test
    public void buscarUsuarioPeloId_ComIdInexistente_lancarException(){ 

        assertThatThrownBy(() -> service.getById(1L)).isInstanceOf(UsuarioNaoEncontradoException.class);
    }

    @Test
    public void buscarUsuarioPeloId_ComIdInvalido_lancarException(){
        assertThatThrownBy(() -> service.getById(-1L)).isInstanceOf(InvalidInputException.class);
    }

    @Test
    public void buscarUsuarioPeloCpf_ComCpfValido_retornarUsuario(){
        when(repository.findByCpf(usuarioPadrao.getCpf())).thenReturn(Optional.of(usuarioPadrao));

        Optional<UsuariosFindDto> dto = service.getByCPF(usuarioPadrao.getCpf());

        assertThat(dto.get().getId()).isEqualTo(1L);
        assertThat(dto.get().getNome()).isEqualTo("Murilo");
        assertThat(dto.get().getEmail()).isEqualTo("teste@gmail.com");
        assertThat(dto.get().getTelefone()).isEqualTo("42999999999");
    }

    @Test
    public void buscarUsuarioPeloCpf_ComCpfInexistente_lancarException(){ 

        assertThatThrownBy(() -> service.getByCPF("")).isInstanceOf(InvalidInputException.class);
        assertThatThrownBy(() -> service.getByCPF(null)).isInstanceOf(InvalidInputException.class);
    }

    @Test
    public void buscarUsuarioPeloCpf_ComCpfInvalido_lancarException(){
        when(repository.findByCpf("1232143223")).thenThrow(UsuarioNaoEncontradoException.class);

        assertThatThrownBy(() -> service.getByCPF("1232143223")).isInstanceOf(UsuarioNaoEncontradoException.class);
    }

    @Test
    public void deletarUsuario_ComIdExistente_retornarVoid(){

        when(repository.existsById(1L)).thenReturn(true);
        assertThatCode(() -> service.deleteById(1L)).doesNotThrowAnyException();
    }

    @Test
    public void deletarUsuario_ComIdInexistente_lancarException(){

        assertThatThrownBy(() -> service.deleteById(1L)).isInstanceOf(UsuarioNaoEncontradoException.class);
    }

    @Test
    public void deletarUsuario_ComIdInvalido_lancarException(){
        assertThatThrownBy(() -> service.deleteById(-1L)).isInstanceOf(InvalidInputException.class);
    }

    @Test
    public void atualizarUsuario_ComIdExistente_retornarUsuario(){
        when(repository.existsById(1L)).thenReturn(true);
        when(repository.save(usuarioPadrao)).thenReturn(usuarioPadrao);

        Optional<Usuarios> dto = service.updateUsuarios(1L, usuarioPadrao);

        assertThat(dto.get().getId()).isEqualTo(1L);
        assertThat(dto.get().getNome()).isEqualTo("Murilo");
        assertThat(dto.get().getEmail()).isEqualTo("teste@gmail.com");
        assertThat(dto.get().getTelefone()).isEqualTo("42999999999");
    }

    @Test
    public void atualizarUsuario_ComIdInexistente_lancarException(){

        assertThatThrownBy(() -> service.updateUsuarios(1L, usuarioPadrao)).isInstanceOf(UsuarioNaoEncontradoException.class);
    }

    @Test
    public void atualizarUsuario_ComIdIvalido_lancarException(){

        assertThatThrownBy(() -> service.updateUsuarios(-1L, usuarioPadrao)).isInstanceOf(InvalidInputException.class);
    }


}
