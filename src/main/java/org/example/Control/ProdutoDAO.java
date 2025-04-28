package org.example.Control;

import org.example.model.BancodeDados;
import org.example.model.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.example.model.BancodeDados.getConnection;

public class ProdutoDAO {

    private final Connection connection;
    public ProdutoDAO() {

        try {
            this.connection = getConnection();
            System.out.println("Conectado com sucesso");
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados");
            throw new RuntimeException(e);

        }
    }

    public void adicionarProduto(Produto produto) throws SQLException {
        String sql = "INSERT INTO produto (nome, descricao, preco, quantidade, tipo_produto, id_fornecedor) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con =getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            // Preenchendo os parâmetros
            stmt.setString(1, produto.getNomeProduto());
            stmt.setString(2, produto.getDescricaoProduto());
            stmt.setDouble(3, produto.getPrecoProduto());
            stmt.setInt(4, produto.getQuantidadeProduto());
            stmt.setString(5, produto.getTipoProduto());
            stmt.setInt(6, produto.getIdFornecedor());

            // Executa a query
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao adicionar produto", e);
        }
    }

    public void editarProduto(Produto produto) throws SQLException {
        String sql = "UPDATE Produto SET nome = ?, descricao = ?, preco = ?, quantidade = ?, tipo_produto = ?, id_fornecedor = ? WHERE id_produto = ?";


        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            // Usando um método auxiliar para setar os parâmetros
            setParameters(stmt, produto);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                System.out.println("erro: produto nao encontrado");
                throw new RuntimeException("erro: produto não encontrado");
            }
            System.out.println("Produto editado com sucesso!");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.err.println("Erro: Fornecedor especificado não existe.");
            throw new RuntimeException("Fornecedor inválido.");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao editar o produto: " + e.getMessage());
        }
    }

    private void setParameters(PreparedStatement stmt, Produto produto) throws SQLException {
        stmt.setString(1, produto.getNomeProduto());
        stmt.setString(2, produto.getDescricaoProduto());
        stmt.setDouble(3, produto.getPrecoProduto());
        stmt.setInt(4, produto.getQuantidadeProduto());
        stmt.setString(5, produto.getTipoProduto());
        stmt.setInt(6, produto.getIdFornecedor());
        stmt.setInt(7, produto.getIdProduto());
    }

    public List<Produto> listarProduto() throws SQLException {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT p.id_produto, p.nome, p.descricao, p.preco, p.quantidade, p.tipo_produto, f.nome AS fornecedor, p.id_fornecedor " +
                "FROM produto p " +
                "JOIN fornecedor f ON p.id_fornecedor = f.id_fornecedor";


        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Produto produto = new Produto(
                        rs.getInt("id_produto"),
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getDouble("preco"),
                        rs.getInt("quantidade"),
                        rs.getString("tipo_produto"),
                        rs.getInt("id_fornecedor"),
                        rs.getString("fornecedor")
                );
                produtos.add(produto);
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return produtos;
    }
    public Produto buscarProdutoPorId(int idProduto) {
        String sql = "SELECT p.id_produto, p.nome, p.descricao, p.preco, p.quantidade, p.tipo_produto, p.id_fornecedor, f.nome AS nome_fornecedor " +
                "FROM Produto p " +
                "JOIN Fornecedor f ON p.id_fornecedor = f.id_fornecedor " +
                "WHERE p.id_produto = ?";
        Produto produto = null;

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idProduto);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                     produto = new Produto(
                            rs.getInt("id_produto"),
                            rs.getString("nome"),
                            rs.getString("descricao"),
                            rs.getDouble("preco"),
                            rs.getInt("quantidade"),
                            rs.getString("tipo_produto"),
                            rs.getInt("id_fornecedor"),
                            rs.getString("nome_fornecedor")  // aqui pega certo
                    );
                    // Agora, setamos o nome do fornecedor
                    produto.setNomeFornecedor(rs.getString("nome_fornecedor"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar o produto: " + e.getMessage());
        }

        return produto;
    }

    public List<Produto> buscarProduto(String nome) throws SQLException {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT p.id_produto, p.nome, p.descricao, p.preco, p.quantidade, p.tipo_produto, p.id_fornecedor, f.nome AS nome_fornecedor " +
                "FROM Produto p " +
                "JOIN Fornecedor f ON p.id_fornecedor = f.id_fornecedor " +
                "WHERE p.nome LIKE ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Produto produto = new Produto(
                        rs.getInt("id_produto"),
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getDouble("preco"),
                        rs.getInt("quantidade"),
                        rs.getString("tipo_produto"),
                        rs.getInt("id_fornecedor"),
                        rs.getString("nome_fornecedor")  // aqui pega certo
                );

                // Se o nome do fornecedor já for passado no construtor, essa linha nem precisa:
                // produto.setNomeFornecedor(rs.getString("nome_fornecedor"));

                produtos.add(produto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtos;
    }


    public String obterNomeFornecedor( int idFornecedor) throws SQLException  {
        String sql = "SELECT nome FROM fornecedor WHERE id_fornecedor = ?";
        try (Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idFornecedor);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("nome");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public boolean fornecedorExist(int idProduto) throws SQLException {
        String sql = "SELECT COUNT(*) FROM fornecedor WHERE id_fornecedor = ?";

        try (Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, idProduto);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return  rs.getInt(1) > 0;
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void atualizarEstoque(int idProduto, int quantidade, boolean isEntrada) throws SQLException {

        String sql = "UPDATE produto SET quantidade = quantidade " + (isEntrada ? "+" : "-") + " ? WHERE id_produto = ?";

        try(Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, quantidade);
            stmt.setInt(2, idProduto);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                throw new RuntimeException("Produto não encontrado.");
            }
            System.out.println("Produto atualizado com sucesso!");
        }catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao atualizar o estoque: " + e.getMessage());
        }
    }
    public String buscarNomeFornecedor(int idFornecedor) throws SQLException {
        String nomeFornecedor = null;
        String sql = "SELECT nome FROM fornecedor WHERE id_fornecedor = ?";  // Ajuste a query conforme sua tabela de fornecedores

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idFornecedor);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    nomeFornecedor = rs.getString("nome");
                }
            }
        }
        return nomeFornecedor;
    }

    public boolean fornecedorExiste(int idFornecedor) {
        String sql = "SELECT COUNT(*) FROM Fornecedor WHERE ID_Fornecedor = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idFornecedor);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0; // Retorna verdadeiro se o fornecedor existe
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;  // Retorna falso se ocorreu algum erro ou fornecedor não encontrado
    }
    public void excluirProduto(int idProduto) {
        String sql = "DELETE FROM produto WHERE id_produto = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idProduto);
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new RuntimeException("Produto não encontrado para exclusão.");
            }

            System.out.println("Produto excluído com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao excluir produto: " + e.getMessage());
        }
    }


}
