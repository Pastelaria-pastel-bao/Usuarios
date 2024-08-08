package com.cadastro.usuarios.cadastrousuarios.web;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cadastro.usuarios.cadastrousuarios.entities.Usuarios;
import com.cadastro.usuarios.cadastrousuarios.service.UsuariosService;
import com.cadastro.usuarios.cadastrousuarios.swagger.SpringDoc;
import com.cadastro.usuarios.dtos.UsuariosFindDto;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping("/api/v1/usuarios")
@Validated
public class UsuarioController implements SpringDoc{

    private final UsuariosService usuarioService;

    public UsuarioController(UsuariosService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public ResponseEntity<Usuarios> criar(@Valid @RequestBody Usuarios u) {
        Usuarios usuario = usuarioService.criar(u);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }


    @Override
    public ResponseEntity<Optional<UsuariosFindDto>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.getById(id));
    }


    @Override
    public ResponseEntity<Optional<UsuariosFindDto>> getByCpf(@PathVariable String cpf) {
        return ResponseEntity.ok(usuarioService.getByCPF(cpf));
    }


    @Override
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        usuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    @Override
    public ResponseEntity<Usuarios> updateUsuarios(@PathVariable Long id, @RequestBody @Valid Usuarios updatedPasteis) {
        Optional<Usuarios> usuario = usuarioService.updateUsuarios(id, updatedPasteis);
        return usuario.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
