package com.cadastro.usuarios.cadastrousuarios.entities;

import org.hibernate.validator.constraints.br.CPF;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Tb_Usuarios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    private String nome;

    @Email
    private String email;

    @CPF
    private String cpf;

    @NotNull
    @NotEmpty
    @Column(length = 11)
    private String telefone;

    @NotNull
    @NotEmpty
    private String senha;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id", nullable = true)
    private Endereco endereco;
}
