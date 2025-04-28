package org.example.Telas.Estoque;

import org.example.Control.ProdutoDAO;
import org.example.Telas.Estoque.Abas.ProdutoCadastro;
import org.example.Telas.Estoque.Abas.abaEntrada;
import org.example.Telas.Estoque.Abas.abaSaida;
import org.example.Telas.Estoque.Abas.editarProduto;
import org.example.model.Produto;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class Estoque extends JFrame {

    private JTable tabelaProdutos;

    private ProdutoDAO produtoDAO;

    public Estoque() throws SQLException {
        setTitle("Estoque");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setResizable(false);

        this.produtoDAO = new ProdutoDAO();

        JPanel fundo = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/img.png")));
                g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this); // Preenche o painel com a imagem
            }
        };

        JPanel centerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.white);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        centerPanel.setBounds(30, 100, 1470, 560);
        centerPanel.setOpaque(false);

        String[] colunas = {"ID", "Nome", "Descrição", "Preço", "Quantidade", "Fornecedor"};
        Object[][] dados = {};

        tabelaProdutos = new JTable(dados, colunas);
        JScrollPane scrollPane = new JScrollPane(tabelaProdutos);
        scrollPane.setBounds(30, 100, 1470, 560);
        tabelaProdutos.setFont(new Font("Tahoma", Font.PLAIN, 16));
        tabelaProdutos.setRowHeight(20);
        fundo.add(scrollPane);


        JButton cadastrarButton = new JButton("CADASTRAR");
        cadastrarButton.setBounds(250, 700, 200, 40);
        cadastrarButton.setFont(new Font("Tahoma", Font.BOLD, 18));
        fundo.add(cadastrarButton);
        cadastrarButton.addActionListener((ActionEvent e) -> {
            SwingUtilities.invokeLater(() -> new ProdutoCadastro(this));  // Passando a referência de Estoque para ProdutoCadastro
        });

        JButton editarButton = new JButton("EDITAR");
        editarButton.setFont(new Font("Tahoma", Font.BOLD, 18));
        editarButton.setBounds(500, 700, 200, 40);


        editarButton.addActionListener((ActionEvent e) -> {
            int linhaSelecionada = tabelaProdutos.getSelectedRow();  // Pega a linha selecionada
            if (linhaSelecionada != -1) {  // Verifica se uma linha foi selecionada
                Produto produto = produtoDAO.buscarProdutoPorId((Integer) tabelaProdutos.getValueAt(linhaSelecionada, 0));  // Buscar o produto pelo ID
                if (produto != null) {
                    new editarProduto(produto, this);  // Passa o produto para a tela de edição
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um produto para editar", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        fundo.add(editarButton);

        ImageIcon iconeExcluir = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Excluir.png")));
        JButton botaoexcluir = new JButton();
        botaoexcluir.setBounds(1100, 25, 60, 60); // Tamanho inicial do botão
        botaoexcluir.setBackground(new Color(39, 78, 197));
        botaoexcluir.setBorderPainted(false);
        botaoexcluir.setFocusable(false);
        botaoexcluir.setContentAreaFilled(false);
        botaoexcluir.addActionListener((ActionEvent e) -> {
            int linhaSelecionada = tabelaProdutos.getSelectedRow();
            if (linhaSelecionada != -1) {
                int opcao = JOptionPane.showConfirmDialog(this,
                        "Tem certeza que deseja excluir este produto?",
                        "Confirmar exclusão", JOptionPane.YES_NO_OPTION);
                if (opcao == JOptionPane.YES_OPTION) {
                    int idProduto = (int) tabelaProdutos.getValueAt(linhaSelecionada, 0);
                    try {
                        produtoDAO.excluirProduto(idProduto);
                        atualizarTabela(); // Atualiza a tabela após a exclusão
                        JOptionPane.showMessageDialog(this, "Produto excluído com sucesso!");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, "Erro ao excluir produto: " + ex.getMessage(),
                                "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um produto para excluir.",
                        "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });


        botaoexcluir.setIcon(redimensionarIcone(iconeExcluir, botaoexcluir.getWidth(), botaoexcluir.getHeight()));

        ImageIcon ListaIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/lista.png")));
        JButton botaoList = new JButton();
        botaoList.setBounds(1200, 25, 60, 60); // Tamanho inicial do botão
        botaoList.setBackground(new Color(39, 78, 197));
        botaoList.setBorderPainted(false);
        botaoList.setFocusable(false);
        botaoList.setContentAreaFilled(false);
        botaoList.addActionListener((ActionEvent e) -> {
            new AbaTarefa();
        });

        botaoList.setIcon(redimensionarIcone(ListaIcon, botaoList.getWidth(), botaoList.getHeight()));




        JButton entradaButton = new JButton("ENTRADA");
        entradaButton.setFont(new Font("Tahoma", Font.BOLD, 18));
        entradaButton.setBounds(750, 700, 200, 40);
        entradaButton.addActionListener((ActionEvent e) -> {
            int linhaSelecionada = tabelaProdutos.getSelectedRow();  // Pega a linha selecionada
            if (linhaSelecionada != -1) {
                Produto produto = produtoDAO.buscarProdutoPorId((Integer) tabelaProdutos.getValueAt(linhaSelecionada, 0));
                if (produto != null) {
                    new abaEntrada(produto, this);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um produto para registrar entrada", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton saidaButton = new JButton("SAÍDA");
        saidaButton.setFont(new Font("Tahoma", Font.BOLD, 18));
        saidaButton.setBounds(1000, 700, 200, 40);
        saidaButton.addActionListener((ActionEvent e) -> {
            int linhaSelecionada = tabelaProdutos.getSelectedRow();  // Pega a linha selecionada
            if (linhaSelecionada != -1) {  // Verifica se uma linha foi selecionada
                Produto produto = produtoDAO.buscarProdutoPorId((Integer) tabelaProdutos.getValueAt(linhaSelecionada, 0));  // Buscar o produto pelo ID
                if (produto != null) {
                    new abaSaida(produto,this);  // Passa o produto para a tela de saída
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um produto para registrar saída", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        JTextField campoBusca = new JTextField();
        campoBusca.setFont(new Font("Tahoma", Font.BOLD, 15));
        campoBusca.setBounds(450, 50, 300, 33);
        campoBusca.addActionListener((ActionEvent e) -> {
            try {
                buscarProduto(campoBusca.getText());
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        fundo.add(campoBusca);


        fundo.setLayout(null);
        setVisible(true);
        add(fundo);
        fundo.add(centerPanel);
        fundo.add(editarButton);
        fundo.add(entradaButton);
        fundo.add(saidaButton);
        fundo.add(cadastrarButton);
        fundo.add(botaoexcluir);
        fundo.add(botaoList);
        preencherTabelaProdutos();


    }

    private static ImageIcon redimensionarIcone(ImageIcon icone, int largura, int altura) {
        Image imagem = icone.getImage();
        Image imagemRedimensionada = imagem.getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
        return new ImageIcon(imagemRedimensionada);
    }


    private void preencherTabelaProdutos() throws SQLException {
        List<Produto> produtos = produtoDAO.listarProduto();
        Object[][] dados = new Object[produtos.size()][7];

        for (int i = 0; i < produtos.size(); i++) {
            Produto produto = produtos.get(i);
            dados[i][0] = produto.getIdProduto();
            dados[i][1] = produto.getNomeProduto();
            dados[i][2] = produto.getDescricaoProduto();
            dados[i][3] = String.format("R$ %.2f", produto.getPrecoProduto());
            dados[i][4] = produto.getQuantidadeProduto();
            dados[i][5] = produto.getTipoProduto();
            String nomeFornecedor = produtoDAO.buscarNomeFornecedor(produto.getIdFornecedor());
            dados[i][6] = nomeFornecedor;
        }

        // Atualizando o modelo da tabela com os dados formatados
        javax.swing.table.DefaultTableModel modelo = new javax.swing.table.DefaultTableModel(dados, new String[] {
                "ID", "Nome", "Descrição", "Preço", "Quantidade","Tipo", "ID Fornecedor"
        }) {
            // Sobrescrevendo o método isCellEditable para garantir que nenhuma célula seja editável
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;  // Impede a edição de todas as células
            }
        };

        tabelaProdutos.setModel(modelo);

        tabelaProdutos.getColumnModel().getColumn(2).setPreferredWidth(300);
        tabelaProdutos.getColumnModel().getColumn(4).setPreferredWidth(40); // Ajuste o valor conforme necessário


        tabelaProdutos.getColumnModel().getColumn(3).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (value instanceof String) {
                    value = "R$ " + value;
                    setText((String) value);
                }
                return component;
            }
        });
    }



    private  void buscarProduto(String nomeProduto) throws SQLException {


        List<Produto> produtos = produtoDAO.buscarProduto(nomeProduto);
        Object[][] dados = new Object[produtos.size()][7];

        for (int i = 0; i < produtos.size(); i++) {
            Produto produto = produtos.get(i);
            dados[i][0] = produto.getIdProduto();
            dados[i][1] = produto.getNomeProduto();
            dados[i][2] = produto.getDescricaoProduto();
            dados[i][3] = produto.getPrecoProduto();
            dados[i][4] = produto.getQuantidadeProduto();
            dados[i][5] = produto.getTipoProduto();
            dados[i][6] = produto.getIdFornecedor();  // Agora exibindo o ID do fornecedor
        }

        tabelaProdutos.setModel(new javax.swing.table.DefaultTableModel(dados, new String[] {
                "ID", "Nome", "Descrição", "Preço", "Quantidade", "Tipo", "ID Fornecedor"  // Alterado para ID Fornecedor
        }));

    }
    public void atualizarTabela() throws SQLException {

            preencherTabelaProdutos();  // Atualiza a tabela com dados mais recentes

    }

   public static void main(String[] args) throws SQLException {
        new Estoque();
   }



}
