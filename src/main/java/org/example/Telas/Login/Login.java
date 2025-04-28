package org.example.Telas.Login;

import org.example.Control.LoginControll;
import org.example.Telas.Estoque.Estoque;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.SQLException;
import java.util.Objects;

public class Login extends JFrame {
    public Login() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
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


        JTextField userText = new JTextField("Digite seu usuário");
        userText.setBounds(180, 120, 250, 40);
        userText.setFont(new Font("Arial", Font.PLAIN, 15));
        userText.setForeground(Color.BLACK);



        JPasswordField passwordText = new JPasswordField("Senha");
        passwordText.setBounds(180, 190, 250, 40);
        passwordText.setFont(new Font("Arial", Font.PLAIN, 15));
        passwordText.setForeground(Color.BLACK);
        passwordText.setEchoChar((char)0);




        JButton loginButton = new JButton("Login");
        loginButton.setBounds(180, 260, 120, 40);

        JButton registerButton = new JButton("Cadastrar");
        registerButton.setBounds(310, 260, 120, 40);

        userText.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if(userText.getText().equals("Digite seu usuário")) {
                    userText.setText("");
                    userText.setForeground(Color.BLACK);
                }
            }
            public void focusLost(FocusEvent e) {
                if(userText.getText().isEmpty()){
                    userText.setText("Digite seu usuário");
                    userText.setForeground(Color.BLACK);
                }
            }

        });

        passwordText.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if(String.valueOf(passwordText.getPassword()).equals("Senha")){
                    passwordText.setText("");
                    passwordText.setEchoChar('*');
                    passwordText.setForeground(Color.BLACK);
                }
            }
            public void focusLost(FocusEvent e) {
                if(String.valueOf(passwordText.getPassword()).isEmpty()){
                    passwordText.setText("Senha");
                    passwordText.setEchoChar((char)0);
                    passwordText.setForeground(Color.BLACK);
                }
            }
        });

        registerButton.addActionListener(e -> {
                Cadastro cadastro = new Cadastro();

        });

        loginButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeUsuario = userText.getText();
                String senhaUsuario = String.valueOf(passwordText.getPassword());


                try {
                    if (LoginControll.validarUser(nomeUsuario,senhaUsuario)){
                        JOptionPane.showMessageDialog(null,"Login efetuado com sucesso!");
                        Estoque estoque = new Estoque();
                        dispose();
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"Usúario ou senha incorretos !!");
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }


        });







        // Atualiza a interface gráfica
        fundo.revalidate();
        fundo.repaint();

        // Adiciona o fundo ao JFrame
        add(fundo);
        setVisible(true);
        fundo.setVisible(true);

        fundo.add(userText);
        fundo.add(passwordText);
        fundo.add(loginButton);
        fundo.add(registerButton);




    }
    public static void main(String[] args) {
        new Login();
    }

}
