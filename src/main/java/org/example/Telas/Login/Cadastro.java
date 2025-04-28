package org.example.Telas.Login;

import org.example.Control.FuncionarioDAO;
import org.example.model.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.SQLException;
import java.util.Objects;

public class Cadastro extends JFrame {



    public Cadastro() {

        setTitle("Cadastro");
        setSize(500, 650);
        setLocationRelativeTo(null);
        setResizable(false);




        JPanel fundo = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/img.png")));
                g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this); // Preenche o painel com a imagem
            }
        };
        fundo.setLayout(null);


        JTextField usuario = new JTextField("Usuário");
        usuario.setBounds(100, 130, 290, 50);
        usuario.setFont(new Font("Arial", Font.PLAIN, 15));
        usuario.setForeground(Color.BLACK);

        JTextField senha = new JTextField("Senha");
        senha.setBounds(100, 200, 290, 50);
        senha.setFont(new Font("Arial", Font.PLAIN, 15));
        senha.setForeground(Color.BLACK);

        JTextField cpf = new JTextField("CPF");
        cpf.setBounds(100, 270, 290, 50);
        cpf.setFont(new Font("Arial", Font.PLAIN, 15));
        cpf.setForeground(Color.BLACK);

        JTextField email = new JTextField("E-mail");
        email.setBounds(100, 340, 290, 50);
        email.setFont(new Font("Arial", Font.PLAIN, 15));
        email.setForeground(Color.BLACK);

        JButton cadastrar = new JButton("Cadastrar");
        cadastrar.setBounds(100, 410, 290, 50);
        cadastrar.setFont(new Font("Arial", Font.PLAIN, 15));
        cadastrar.addActionListener(e -> {
            try {
                cadastrarCliente(usuario, senha, cpf, email);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        usuario.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if(usuario.getText().equals("Usuário")) {
                    usuario.setText("");
                    usuario.setForeground(Color.BLACK);
                }
            }
            public void focusLost(FocusEvent e) {
                if(usuario.getText().isEmpty()){
                    usuario.setText("Usuário");
                    usuario.setForeground(Color.BLACK);
                }
            }

        });

        senha.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if(senha.getText().equals("Senha")) {
                    senha.setText("");
                    senha.setForeground(Color.BLACK);
                }
            }
            public void focusLost(FocusEvent e) {
                if(senha.getText().isEmpty()){
                    senha.setText("Senha");
                    senha.setForeground(Color.BLACK);
                }
            }

        });

        cpf.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if(cpf.getText().equals("CPF")) {
                    cpf.setText("");
                    cpf.setForeground(Color.BLACK);
                }
            }
            public void focusLost(FocusEvent e) {
                if(cpf.getText().isEmpty()){
                    cpf.setText("CPF");
                    cpf.setForeground(Color.BLACK);
                }
            }

        });

        email.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if(email.getText().equals("E-mail")) {
                    email.setText("");
                    email.setForeground(Color.BLACK);
                }
            }
            public void focusLost(FocusEvent e) {
                if(email.getText().isEmpty()){
                    email.setText("E-mail");
                    email.setForeground(Color.BLACK);
                }
            }

        });










        setVisible(true);

        add(fundo);
        fundo.add(usuario);
        fundo.add(senha);
        fundo.add(cpf);
        fundo.add(email);
        fundo.add(cadastrar);



    }



    protected void cadastrarCliente(JTextField usuario, JTextField senha, JTextField cpf, JTextField email) throws SQLException {

        try {
            if (usuario.getText().trim().isEmpty() || senha.getText().trim().isEmpty() || cpf.getText().trim().isEmpty() || email.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!validarCPF(cpf.getText().trim())) {
                JOptionPane.showMessageDialog(this, "CPF inválido. Por favor, insira um CPF válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
        }    if (!validarEmail(email.getText().trim())) {
                JOptionPane.showMessageDialog(this, "Email inválido. Por favor, insira um email válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
            if (funcionarioDAO.verificarCPFExistente(cpf.getText())) {
                JOptionPane.showMessageDialog(this, "CPF já cadastrado. Tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (funcionarioDAO.verificarEmailExistente(email.getText())) {
                JOptionPane.showMessageDialog(this, "Email já cadastrado. Tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            FuncionarioDAO funcionario = new FuncionarioDAO();
            Usuario user = new Usuario(usuario.getText(), senha.getText(), cpf.getText(), email.getText());
            funcionario.adicionarUsuario(user);

            JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso!");


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    // Método para validar o CPF
    private boolean validarCPF(String cpf) {
        String regex = "^(\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}|\\d{11})$";
        return cpf.matches(regex);
    }

    // Método para validar o email
    private boolean validarEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(regex);
    }

    public static void main(String[] args) {
    new Cadastro();}
}
