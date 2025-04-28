package org.example.Telas.Estoque.Abas;

import org.example.Control.ProdutoDAO;
import org.example.Telas.Estoque.Estoque;
import org.example.model.Produto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Objects;

public class abaSaida extends JFrame {

    private Estoque estoque;
    public abaSaida(Produto produto, Estoque estoque) {
        super(" Saida");
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
        label.setBounds(100, 20, 200, 30);

        JTextField saidaProdu = new JTextField("QUANTIDADE");
        saidaProdu.setBounds(105,85,200,40);
        saidaProdu.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (saidaProdu.getText().equals("QUANTIDADE")){
                    saidaProdu.setText("");
                    saidaProdu.setForeground(Color.BLACK);
                }

            }
        });



        JButton botaoSiada = new JButton("RETIRAR");
        botaoSiada .setBounds(118,170,170,40);
        botaoSiada.addActionListener(e -> {
            try {
                int quantidade = Integer.parseInt(saidaProdu.getText().trim());


                if (quantidade <= 0) {
                    JOptionPane.showMessageDialog(this, "A quantidade deve ser maior que zero!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                ProdutoDAO produtoDAO = new ProdutoDAO();
                // Supondo que o ID do Funcionário seja 1 (você pode modificar para pegar da sessão ou login do usuário)
                int idFuncionario = 1;
                // Atualiza o estoque e registra a movimentação de saída
                produtoDAO.atualizarEstoque(produto.getIdProduto(), quantidade, false);

                JOptionPane.showMessageDialog(this, "Saída registrada com sucesso!");
                estoque.atualizarTabela();
                dispose();  // Atualiza a tabela após a saída
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Digite uma quantidade válida.", "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao registrar saída: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(label);
        panel.add(botaoSiada);
        panel.add(saidaProdu);
        setLocationRelativeTo(null);
        panel.setLayout(null);
        setResizable(false);
        add(panel);
        setVisible(true);

    }
    public static void main(String[] args) {

    }


}
