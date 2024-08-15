package com.cadastro.usuarios.cadastrousuarios.entities.endereco;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoResponse {
    private String cep;
    private String state;
    private String city;
    private String neighborhood;
    private String street;



}
