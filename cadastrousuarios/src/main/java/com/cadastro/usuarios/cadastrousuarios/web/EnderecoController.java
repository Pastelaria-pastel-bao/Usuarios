package com.cadastro.usuarios.cadastrousuarios.web;

import com.cadastro.usuarios.cadastrousuarios.entities.endereco.Endereco;
import com.cadastro.usuarios.cadastrousuarios.entities.EnderecoPersist;
import com.cadastro.usuarios.cadastrousuarios.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @PostMapping
    public EnderecoPersist adicionarEndereco(@RequestBody Endereco endereco) {
        return enderecoService.adicionarEndereco(endereco);
    }

}
