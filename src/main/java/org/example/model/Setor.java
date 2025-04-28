package org.example.model;

public class Setor {

    private Integer idSetor;
    private String nome;
    private String tipo;

    public Setor() {}

    public Setor(Integer idSetor, String nome, String tipo) {
        this.idSetor = idSetor;
        this.nome = nome;
        this.tipo = tipo;
    }

    public Integer getIdSetor() {
        return idSetor;
    }

    public void setIdSetor(Integer idSetor) {
        this.idSetor = idSetor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
