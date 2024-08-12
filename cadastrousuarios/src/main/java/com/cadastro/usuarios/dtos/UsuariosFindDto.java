package com.cadastro.usuarios.dtos;

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

}
