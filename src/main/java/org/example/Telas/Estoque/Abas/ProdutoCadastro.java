package org.example.Telas.Estoque.Abas;

import org.example.Control.ProdutoDAO;
import org.example.Telas.Estoque.Estoque;
import org.example.model.Produto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Objects;

public class ProdutoCadastro extends JFrame {

    private Estoque estoque;


    public ProdutoCadastro(Estoque estoque) {

        System.out.println("Referência de Estoque: " + this.estoque);  // Depuração


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


        JTextField nomeProduto = new JTextField("Nome");
        nomeProduto.setBounds(100, 60, 290, 50);
        nomeProduto.setFont(new Font("Arial", Font.PLAIN, 15));
        nomeProduto.setForeground(Color.BLACK);

        JTextField descricaoProduto = new JTextField("Descrição");
        descricaoProduto.setBounds(100, 130, 290, 50);
        descricaoProduto.setFont(new Font("Arial", Font.PLAIN, 15));
        descricaoProduto.setForeground(Color.BLACK);

        JTextField precoProduto = new JTextField("Preço");
        precoProduto.setBounds(100, 200, 290, 50);
        precoProduto.setFont(new Font("Arial", Font.PLAIN, 15));
        precoProduto.setForeground(Color.BLACK);

        JTextField quantidadeProduto = new JTextField("Quantidade");
        quantidadeProduto.setBounds(100, 270, 290, 50);
        quantidadeProduto.setFont(new Font("Arial", Font.PLAIN, 15));
        quantidadeProduto.setForeground(Color.BLACK);

        JTextField tipo_produto = new JTextField("Tipo");
        tipo_produto.setBounds(100, 340, 290, 50);
        tipo_produto.setFont(new Font("Arial", Font.PLAIN, 15));

        JTextField forncedorProduto = new JTextField("Código Forncedor");
        forncedorProduto.setBounds(100, 410, 290, 50);
        forncedorProduto.setFont(new Font("Arial", Font.PLAIN, 15));


        JButton cadastrar = new JButton("Cadastrar");
        cadastrar.setBounds(100, 500, 290, 50);
        cadastrar.setFont(new Font("Arial", Font.PLAIN, 15));
        cadastrar.addActionListener((ActionEvent e) -> {
            try {
                // Pegando os valores dos campos
                String nome = nomeProduto.getText().trim();
                String descricao = descricaoProduto.getText().trim();
                String precoText = precoProduto.getText().trim();
                String quantidadeText = quantidadeProduto.getText().trim();
                String tipoText = tipo_produto.getText().trim();
                String fornecedorText = forncedorProduto.getText().trim();

                // 1. Verificar se Nome e Descrição estão preenchidos
                if (nome.equals("NOME") || nome.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "O nome do produto é obrigatório!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (descricao.equals("DESCRIÇÃO") || descricao.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "A descrição do produto é obrigatória!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (tipoText.equals("TIPO") || tipoText.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "O tipo é obrigatório!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // 2. Verificar se o preço e a quantidade são válidos
                double preco = -1;
                int quantidade = -1;

                try {
                    if (precoText.equals("PREÇO") || precoText.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "O preço é obrigatório!", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    preco = Double.parseDouble(precoText);
                    if (preco <= 0) {
                        JOptionPane.showMessageDialog(this, "O preço deve ser maior que zero!", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Preço inválido, por favor insira um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    if (quantidadeText.equals("QUANTIDADE") || quantidadeText.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "A quantidade é obrigatória!", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    quantidade = Integer.parseInt(quantidadeText);
                    if (quantidade < 0) {
                        JOptionPane.showMessageDialog(this, "A quantidade não pode ser negativa!", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Quantidade inválida, por favor insira um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // 3. Verificar se o ID do fornecedor é válido
                int idFornecedor = -1;
                try {
                    if (fornecedorText.equals("FORNECEDOR") || fornecedorText.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "O ID do fornecedor é obrigatório!", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    idFornecedor = Integer.parseInt(fornecedorText);
                    if (!fornecedorExiste(idFornecedor)) {
                        JOptionPane.showMessageDialog(this, "Fornecedor com ID " + idFornecedor + " não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "ID do Fornecedor inválido, por favor insira um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // 4. Se todos os campos estiverem corretos, cadastrar o produto
                Produto produto = new Produto(0, nome, descricao, preco, quantidade, tipoText, idFornecedor);
                ProdutoDAO produtoDAO = new ProdutoDAO();
                produtoDAO.adicionarProduto(produto);

                // Verifica se estoque não é nulo e chama a atualização da tabela
                if (estoque != null) {
                    estoque.atualizarTabela();  // Atualiza a tabela na tela Estoque
                }

                JOptionPane.showMessageDialog(this, "Produto cadastrado com sucesso!");
                dispose();  // Fecha a tela de cadastro
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar o produto: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });


        nomeProduto.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if(nomeProduto.getText().equals("Nome")) {
                    nomeProduto.setText("");
                    nomeProduto.setForeground(Color.BLACK);
                }
            }
            public void focusLost(FocusEvent e) {
                if(nomeProduto.getText().isEmpty()){
                    nomeProduto.setText("Nome");
                    nomeProduto.setForeground(Color.BLACK);
                }
            }

        });

        descricaoProduto.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if(descricaoProduto.getText().equals("Descrição")) {
                    descricaoProduto.setText("");
                    descricaoProduto.setForeground(Color.BLACK);
                }
            }
            public void focusLost(FocusEvent e) {
                if(descricaoProduto.getText().isEmpty()){
                    descricaoProduto.setText("Descrição");
                    descricaoProduto.setForeground(Color.BLACK);
                }
            }

        });

        precoProduto.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if(precoProduto.getText().equals("Preço")) {
                    precoProduto.setText("");
                    precoProduto.setForeground(Color.BLACK);
                }
            }
            public void focusLost(FocusEvent e) {
                if(precoProduto.getText().isEmpty()){
                    precoProduto.setText("Preço");
                    precoProduto.setForeground(Color.BLACK);
                }
            }

        });

        quantidadeProduto.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if(quantidadeProduto.getText().equals("Quantidade")) {
                    quantidadeProduto.setText("");
                    quantidadeProduto.setForeground(Color.BLACK);
                }
            }
            public void focusLost(FocusEvent e) {
                if(quantidadeProduto.getText().isEmpty()){
                    quantidadeProduto.setText("Quantidade");
                    quantidadeProduto.setForeground(Color.BLACK);
                }
            }

        });

        tipo_produto.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if(tipo_produto.getText().equals("Tipo")) {
                    tipo_produto.setText("");
                    tipo_produto.setForeground(Color.BLACK);
                }
            }
            public void focusLost(FocusEvent e) {
                if(tipo_produto.getText().isEmpty()){
                    tipo_produto.setText("Tipo");
                    tipo_produto.setForeground(Color.BLACK);
                }
            }

        });

        forncedorProduto.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if(forncedorProduto.getText().equals("Código Forncedor")) {
                    forncedorProduto.setText("");
                    forncedorProduto.setForeground(Color.BLACK);
                }
            }
            public void focusLost(FocusEvent e) {
                if(forncedorProduto.getText().isEmpty()){
                    forncedorProduto.setText("Código Forncedor");
                    forncedorProduto.setForeground(Color.BLACK);
                }
            }

        });










        setVisible(true);

        add(fundo);
        fundo.add(nomeProduto);
        fundo.add(descricaoProduto);
        fundo.add(precoProduto);
        fundo.add(quantidadeProduto);
        fundo.add(tipo_produto);
        fundo.add(cadastrar);
        fundo.add(forncedorProduto);



    }
    private boolean fornecedorExiste(int idFornecedor) {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        return produtoDAO.fornecedorExiste(idFornecedor);
    }


}
