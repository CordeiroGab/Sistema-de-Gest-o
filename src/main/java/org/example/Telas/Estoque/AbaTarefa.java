package org.example.Telas.Estoque;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Objects;

public class AbaTarefa extends JFrame {
    private JPanel fundo;
    private int y = 60; // posição vertical das tarefas
    private Connection conn;

    public AbaTarefa() {
        setTitle("Marcadores de Tarefas");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setResizable(false);

        fundo = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/img.png")));
                g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        fundo.setLayout(null);

        JButton btnAdicionar = new JButton("Adicionar Tarefa");
        btnAdicionar.setBounds(20, 20, 160, 30);
        fundo.add(btnAdicionar);

        btnAdicionar.addActionListener(e -> {
            String nome = JOptionPane.showInputDialog("Nome da Tarefa:");
            if (nome != null && !nome.trim().isEmpty()) {
                adicionarTarefa(nome, false);
                salvarNoBD(nome, false);
            }
        });

        conectarBD();
        carregarTarefas();

        add(fundo);
        setVisible(true);
    }

    private void adicionarTarefa(String nome, boolean concluida) {
        JCheckBox checkBox = new JCheckBox(nome, concluida);
        checkBox.setBounds(20, y, 200, 25);
        checkBox.setOpaque(false);

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(230, y, 100, 25);

        btnExcluir.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Deseja excluir esta tarefa?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                fundo.remove(checkBox);
                fundo.remove(btnExcluir);
                fundo.repaint();
                excluirTarefaDoBD(nome);
            }
        });

        checkBox.addItemListener(e -> atualizarStatusNoBD(nome, checkBox.isSelected()));

        fundo.add(checkBox);
        fundo.add(btnExcluir);
        fundo.repaint();
        y += 30;
    }

    private void conectarBD() {
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Estoque", "postgres", "1234");
            Statement st = conn.createStatement();
            st.execute("CREATE TABLE IF NOT EXISTS tarefas (" +
                    "id SERIAL PRIMARY KEY, " +
                    "nome TEXT NOT NULL, " +
                    "concluida BOOLEAN DEFAULT FALSE)");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void salvarNoBD(String nome, boolean concluida) {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO tarefas (nome, concluida) VALUES (?, ?)");
            ps.setString(1, nome);
            ps.setBoolean(2, concluida);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void atualizarStatusNoBD(String nome, boolean concluida) {
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE tarefas SET concluida = ? WHERE nome = ?");
            ps.setBoolean(1, concluida);
            ps.setString(2, nome);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void excluirTarefaDoBD(String nome) {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM tarefas WHERE nome = ?");
            ps.setString(1, nome);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void carregarTarefas() {
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT nome, concluida FROM tarefas");
            while (rs.next()) {
                String nome = rs.getString("nome");
                boolean concluida = rs.getBoolean("concluida");
                adicionarTarefa(nome, concluida);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new AbaTarefa();
    }
}
