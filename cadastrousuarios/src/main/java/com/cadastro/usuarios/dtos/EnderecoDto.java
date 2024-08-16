package com.cadastro.usuarios.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDto {

    @NotNull
    private String cep;

    @NotNull
    private String estado;

    @NotNull
    private String cidade;

    @NotNull
    private String bairro;


    private String numero;

    private String complemento;

    private Long usuarioId;
}
