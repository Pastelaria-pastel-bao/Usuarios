package com.cadastro.usuarios.cadastrousuarios.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String cep;

    @NotNull
    private String estado;

    @NotNull
    private String cidade;

    @NotNull
    private String bairro;

    @NotNull
    private String logradouro;

    private String numero;
    private String complemento;

    @OneToOne(mappedBy = "endereco")
    private Usuarios usuario;


}
