package com.cadastro.usuarios.cadastrousuarios.exeptions;
public class DatabaseException extends RuntimeException {
    public DatabaseException(String message) {
        super(message);
    }
}
