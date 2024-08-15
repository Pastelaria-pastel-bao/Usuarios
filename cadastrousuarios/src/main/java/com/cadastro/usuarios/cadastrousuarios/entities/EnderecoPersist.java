package com.cadastro.usuarios.cadastrousuarios.entities;

import com.cadastro.usuarios.cadastrousuarios.entities.endereco.Bairro;
import jakarta.persistence.*;
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
public class EnderecoPersist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario_id")
    private Long usuarioId;

    @ManyToOne
    @JoinColumn(name = "bairro_id", nullable = false)
    private Bairro bairro;

    private String numero;

    private String complemento;

}
