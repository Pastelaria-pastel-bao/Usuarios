package com.cadastro.usuarios.cadastrousuarios.swagger;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.cadastro.usuarios.cadastrousuarios.entities.Usuarios;
import com.cadastro.usuarios.dtos.UsuariosFindDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Usuarios", description = "Endpoints para gerenciamento de usuario")
public interface SpringDoc {

    @Operation(summary = "Cria Usuario", description = "Cria um Usuario",
    tags = {"Usuarios"},
    responses = {
        @ApiResponse(description = "Criado", responseCode = "201",
        content = {
            @Content(
                mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = Usuarios.class)) 
            )
        }),
        @ApiResponse(description = "Unprocessable Entity", responseCode = "422", content = @Content),
        @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
    })
    public ResponseEntity<Usuarios> criar(@Valid @RequestBody Usuarios u);

    @Operation(summary = "Busca pelo Id", description = "Busca um usuario pelo Id",
    tags = {"Usuarios"},
    responses = {
        @ApiResponse(description = "Ok", responseCode = "200",
        content = {
            @Content(
                mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = Usuarios.class)) 
            )
        }),
        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
        @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
    })
    public ResponseEntity<Optional<UsuariosFindDto>> getById(@PathVariable Long id);

    @Operation(summary = "Busca pelo Cpf", description = "Busca um usuario pelo Cpf",
    tags = {"Usuarios"},
    responses = {
        @ApiResponse(description = "Ok", responseCode = "200",
        content = {
            @Content(
                mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = Usuarios.class)) 
            )
        }),
        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
        @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
    })
    public ResponseEntity<Optional<UsuariosFindDto>> getByCpf(@PathVariable String cpf);

    @Operation(summary = "Deleta pelo Id", description = "Deleta um usuario pelo Id",
    tags = {"Usuarios"},
    responses = {
        @ApiResponse(description = "No Content", responseCode = "204",
        content = {
            @Content(
                mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = Usuarios.class)) 
            )
        }),
        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
        @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
    })
    public ResponseEntity<Void> deleteById(@PathVariable Long id);

    @Operation(summary = "Atualiza pelo Id", description = "Atualiza um usuario pelo Id",
    tags = {"Usuarios"},
    responses = {
        @ApiResponse(description = "Ok", responseCode = "200",
        content = {
            @Content(
                mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = Usuarios.class)) 
            )
        }),
        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
        @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
    })
    public ResponseEntity<Usuarios> updateUsuarios(@PathVariable Long id, @RequestBody @Valid Usuarios updatedPasteis);
}
