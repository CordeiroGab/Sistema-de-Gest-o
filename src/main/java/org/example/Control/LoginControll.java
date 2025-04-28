package org.example.Control;


import org.example.model.BancodeDados;
import org.example.model.Usuario;

import java.sql.SQLException;

public class LoginControll {

    public static boolean validarUser(String nomeUsuario, String senha) throws SQLException {
        Usuario usuario = BancodeDados.validarUsuario(nomeUsuario, senha);

        if (usuario != null) {
            return true;
        }
        else {
            return false;
        }
    }
}
