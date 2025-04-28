package org.example.model;

import org.w3c.dom.Text;

public class Fornecedor {

    private Integer idFornecedor;
    private String nome;
    private Text descricao;

    public String telefone;
    private String email;

    public Fornecedor() {}

    public Fornecedor(Integer idFornecedor, String nome, Text descricao, String telefone, String email) {
        this.idFornecedor = idFornecedor;
        this.nome = nome;
        this.descricao = descricao;
        this.telefone = telefone;
        this.email = email;
    }

    public Integer getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(Integer idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Text getDescricao() {
        return descricao;
    }

    public void setDescricao(Text descricao) {
        this.descricao = descricao;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
