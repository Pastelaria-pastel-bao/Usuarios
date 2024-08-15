package com.cadastro.usuarios.dtos;

import com.cadastro.usuarios.cadastrousuarios.entities.endereco.Endereco;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @RequiredArgsConstructor @Getter @Setter
public class UsuariosFindDto {
    private Long Id;
    private String nome;
    private String email;
    private String telefone;
    private Endereco endereco;

}
