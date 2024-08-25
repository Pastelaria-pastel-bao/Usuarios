package com.cadastro.usuarios.cadastrousuarios.web;

import java.util.Optional;

import com.cadastro.usuarios.cadastrousuarios.entities.endereco.EnderecoResponse;
import com.cadastro.usuarios.cadastrousuarios.exeptions.UsuarioJaCadastradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cadastro.usuarios.cadastrousuarios.entities.Usuarios;
import com.cadastro.usuarios.cadastrousuarios.service.UsuariosService;
import com.cadastro.usuarios.cadastrousuarios.swagger.SpringDoc;
import com.cadastro.usuarios.dtos.UsuariosFindDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;


@Slf4j
@RestController
@RequestMapping("/api/v1/usuarios")
@Validated
public class UsuarioController implements SpringDoc{

    private final UsuariosService usuarioService;

    public UsuarioController(UsuariosService usuarioService) {
        this.usuarioService = usuarioService;
    }


    @PostMapping
    public ResponseEntity<?> criarUsuario(@Valid @RequestBody Usuarios usuario) {
        try {
            Usuarios novoUsuario = usuarioService.criarUsuario(usuario);
            return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
        } catch (UsuarioJaCadastradoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{cep}")
    public Mono<EnderecoResponse> getEnderecoPorCep(@PathVariable String cep) {
        return usuarioService.buscarEnderecoPorCep(cep);
    }


    @Override
    @GetMapping("/id/{id}")
    public ResponseEntity<Optional<UsuariosFindDto>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.getById(id));
    }


    @Override
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Optional<UsuariosFindDto>> getByCpf(@PathVariable String cpf) {
        return ResponseEntity.ok(usuarioService.getByCPF(cpf));
    }


    @Override
    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        usuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    @Override
    @PatchMapping("/id/{id}")
    public ResponseEntity<Usuarios> updateUsuarios(@PathVariable Long id, @RequestBody @Valid Usuarios updatedPasteis) {
        Optional<Usuarios> usuario = usuarioService.updateUsuarios(id, updatedPasteis);
        return usuario.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/email")
    public ResponseEntity<Void> recuperarSenha(@RequestBody @Email String email){
        usuarioService.recuperarSenha(email);
        return ResponseEntity.noContent().build();
    }
}
