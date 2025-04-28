package org.example.model;

import java.sql.*;

public class BancodeDados {

    public static final String URL = "jdbc:postgresql://localhost:5432/Estoque";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1234";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL,USER,PASSWORD);
    }

    public static Usuario validarUsuario(String nomeUsuario, String senha) {
        String sql = "SELECT * FROM usuario WHERE nome = ? and senha = ?";

        try(Connection connection = getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)){

            stmt.setString(1,nomeUsuario);
            stmt.setString(2,senha);

            ResultSet rs = stmt.executeQuery();
            if(rs.next()){

                return new Usuario(
                       rs.getInt("id_usuario"),
                        rs.getString("nome"),
                        rs.getString("senha"),
                        rs.getString("cpf"),
                        rs.getString("email")

                );
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }



}
