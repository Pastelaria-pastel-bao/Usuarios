package com.cadastro.usuarios.cadastrousuarios.exeptions;

public class UsuarioJaCadastradoException extends RuntimeException {
    public UsuarioJaCadastradoException(String message) {
        super(message);
    }
}