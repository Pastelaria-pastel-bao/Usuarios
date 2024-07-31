package com.cadastro.usuarios.cadastrousuarios.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/usuarios")
@Validated
public class UsuarioController {

    private final UsuarioService usuarioService;

    public ResponseEntity<Usuarios> criar(@Valid  @RequestBody Usuarios u) {
        Usuarios usuario = usuarioService.criar(u);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }


    public ResponseEntity<Optional<Usuarios>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.getById(id));
    }


    public ResponseEntity<Optional<Usuarios>> getByNome(@PathVariable String nome) {
        return ResponseEntity.ok(usuarioService.getByNome(nome));
    }


    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        usuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    public ResponseEntity<Usuarios> updateUsuarios(@PathVariable Long id, @RequestBody Usuarios updatedPasteis) {
        Optional<Usuarios> usuario = usuarioService.updatePasteisPartial(id, updatedPasteis);
        return usuario.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
