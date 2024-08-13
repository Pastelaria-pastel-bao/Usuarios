package com.cadastro.usuarios.cadastrousuarios.web;

import com.cadastro.usuarios.cadastrousuarios.entities.Endereco;
import com.cadastro.usuarios.cadastrousuarios.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @PostMapping
    public Endereco adicionarEndereco(@RequestBody Endereco endereco) {
        return enderecoService.adicionarEndereco(endereco);
    }

}
