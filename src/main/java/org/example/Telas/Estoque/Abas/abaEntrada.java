package org.example.Telas.Estoque.Abas;

import org.example.Control.ProdutoDAO;
import org.example.Telas.Estoque.Estoque;
import org.example.Telas.Login.Login;
import org.example.model.Produto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.SQLException;
import java.util.Objects;

public class abaEntrada extends JFrame {

    private final Estoque estoque;

    public abaEntrada(Produto produto, Estoque estoque) {
        super(" ENTRADA");
        this.estoque = estoque;



        setSize(400,300);

        JPanel panel = new JPanel(){
            public void paintComponent(Graphics g){
                super.paintComponent(g);

                ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/img.png")));
                g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        JLabel label = new JLabel("Produto: " + produto.getNomeProduto());
        label.setBounds(100, 50, 200, 30);

        JTextField entradaProduto = new JTextField("QUANTIDADE");
        entradaProduto.setBounds(105,85,200,40);
        entradaProduto.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (entradaProduto.getText().equals("QUANTIDADE")){
                    entradaProduto.setText("");
                    entradaProduto.setForeground(Color.BLACK);
                }

            }
        });

        JButton adicProdu = new JButton("ADICIONAR");
        adicProdu.setBounds(118,170,170,40);
        adicProdu.addActionListener(e ->{

            try{
                int quantidade = Integer.parseInt(entradaProduto.getText());
                if (quantidade <= 0){
                    JOptionPane.showMessageDialog(this, "A quantidade deve ser maior que zero!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                ProdutoDAO produtoDAO = new ProdutoDAO();

                int idFuncionario = 1;

                produtoDAO.atualizarEstoque(produto.getIdProduto(), quantidade, true);
                JOptionPane.showMessageDialog(this, "Entrada registrada com sucesso!");
                estoque.atualizarTabela();
                dispose();


            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao adicionar entrada: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                throw new RuntimeException(ex);
            }

        });

        panel.add(label);
        panel.add(entradaProduto);
        panel.add(adicProdu);
        setLocationRelativeTo(null);
        panel.setLayout(null);
        setResizable(false);
        add(panel);
        setVisible(true);



    }

    public static void main(String[] args) {

    }


}
