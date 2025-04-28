package org.example.Control;

import org.example.model.BancodeDados;
import org.example.model.Usuario;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {

    private final Connection connection;
    public FuncionarioDAO() {

        try {
            this.connection = BancodeDados.getConnection();
            System.out.println("Conectado com sucesso");
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados");
            throw new RuntimeException(e);

        }
    }

    public List<Usuario> ListarFuncionarios() throws SQLException {
        List<Usuario> funcionarios = new ArrayList<>();
        String sql = "SELECT * FROM usuario";

        try(PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet resultSet = stmt.executeQuery()){


            while (resultSet.next()) {
                Usuario usuario = new Usuario(
                        resultSet.getInt("id_usuario"),
                        resultSet.getString("nome"),
                        resultSet.getString("senha"),
                        resultSet.getString("cpf"),
                        resultSet.getString("email")


                );
                funcionarios.add(usuario);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return funcionarios;
    }
    public  void adicionarUsuario(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuario (nome, senha, cpf, email) VALUES (?, ?, ?, ?)";
        if (verificarCPFExistente(usuario.getCpf())) {
            JOptionPane.showMessageDialog(null, "O CPF informado já está cadastrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            return; // Não prosseguir com a inserção
        }

        if (verificarEmailExistente(usuario.getEmailUsuario())) {
            JOptionPane.showMessageDialog(null, "O Email informado já está cadastrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            return; // Não prosseguir com a inserção
        }

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,usuario.getNomeUsuario());
            preparedStatement.setString(2, usuario.getSenhaUsuario());
            preparedStatement.setString(3, usuario.getCpf());
            preparedStatement.setString(4, usuario.getEmailUsuario());
            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean verificarEmailExistente(String email) throws SQLException {
        String sql = "SELECT COUNT(*) FROM usuario WHERE email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Retorna true se o Email já existir
            }
        }
        return false;
    }
    public boolean verificarCPFExistente(String cpf) throws SQLException {
        String sql = "SELECT COUNT(*) FROM usuario WHERE cpf = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Retorna true se o CPF já existir
            }
        }
        return false;
    }
}
