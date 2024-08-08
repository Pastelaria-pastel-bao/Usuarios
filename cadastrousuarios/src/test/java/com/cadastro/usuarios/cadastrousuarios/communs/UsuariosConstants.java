package com.cadastro.usuarios.cadastrousuarios.communs;

import com.cadastro.usuarios.cadastrousuarios.entities.Usuarios;

public class UsuariosConstants {

    public static Usuarios usuarioPadrao = new Usuarios(1L, "Murilo","murilomazur@hotmail.com","11111111111", "42999999999", "Av. Castelo Branco","teste");
    public static Usuarios usuarioCriar = new Usuarios(null, "Murilo","murilomazur@hotmail.com","11111111111", "42999999999", "Av. Castelo Branco","teste");
    public static Usuarios usuarioNulo = new Usuarios(null, null, null, null, null, null, null);
    public static Usuarios usuarioVazio = new Usuarios();
}
