package com.cadastro.usuarios.cadastrousuarios.service;

import static org.mockito.Mockito.when;
import static com.cadastro.usuarios.cadastrousuarios.communs.UsuariosConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cadastro.usuarios.cadastrousuarios.entities.Usuarios;
import com.cadastro.usuarios.cadastrousuarios.exeptions.DatabaseException;
import com.cadastro.usuarios.cadastrousuarios.exeptions.InvalidInputException;
import com.cadastro.usuarios.cadastrousuarios.repositories.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @InjectMocks
    private UsuariosService service;

    @Mock
    private UsuarioRepository repository;

    @Test
    public void criarUsuario_ComDadosValidos_retornarUsuario(){
        when(service.criar(usuarioCriar)).thenReturn(usuarioPadrao);

       Usuarios teste = service.criar(usuarioCriar);

       assertThat(teste).isEqualTo(usuarioPadrao);
    }

    @Test
    public void criarUsuario_ComDadosInvalidos_lancarException(){
        when(service.criar(usuarioNulo)).thenThrow(new DatabaseException("Dados Invalidos"));

        assertThatThrownBy(() -> service.criar(usuarioNulo)).isInstanceOf(DatabaseException.class);

        when(service.criar(usuarioVazio)).thenThrow(new DatabaseException("Dados Invalidos"));

        assertThatThrownBy(() -> service.criar(usuarioVazio)).isInstanceOf(DatabaseException.class);
    }
}
