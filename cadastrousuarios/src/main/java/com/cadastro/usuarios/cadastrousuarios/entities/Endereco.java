package com.cadastro.usuarios.cadastrousuarios.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Tb_Endereco")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @JsonIgnore
    @Column(name = "usuario_id")
    private Long usuarioId;
}
