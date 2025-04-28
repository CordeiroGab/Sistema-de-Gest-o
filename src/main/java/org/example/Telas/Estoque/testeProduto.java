package org.example.Telas.Estoque;

import org.example.Control.ProdutoDAO;
import org.example.model.Produto;

import java.sql.SQLException;
import java.util.List;

public class testeProduto {

        public static void main(String[] args) throws SQLException {
            ProdutoDAO dao = new ProdutoDAO();
            List<Produto> produtos = dao.listarProduto();
            for (Produto p : produtos) {
                System.out.println("Produto: " + p.getNomeProduto());

        }
    }
}
