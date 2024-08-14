package com.cadastro.usuarios.cadastrousuarios.entities;

import org.hibernate.validator.constraints.br.CPF;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @NotEmpty(message = "Nome não pode estar vazio")
    private String nome;

    @Email(message = "Email deve ser válido")
    private String email;

    @Column(name = "cpf", unique = true)
    @CPF(message = "CPF deve ser válido")
    private String cpf;

    @NotNull
    @NotEmpty(message = "Telefone não pode estar vazio")
    @Size(min = 11, max = 11, message = "Telefone deve ter 11 dígitos")
    private String telefone;

    @NotNull
    @NotEmpty(message = "Senha não pode estar vazia")
    @Size(min = 6, message = "Senha deve ter pelo menos 6 caracteres")
    private String senha;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id", nullable = true)
    private Endereco endereco;
}
