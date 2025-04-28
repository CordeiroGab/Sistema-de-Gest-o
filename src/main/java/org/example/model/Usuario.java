package org.example.model;

public class Usuario {

    private int idUsuario;

    private String nomeUsuario;

    private String senhaUsuario;
    private String cpf;
    private String emailUsuario;

    public Usuario() {}
    public Usuario(int idUsuario, String nome, String senha, String cpf, String email) {
        this.idUsuario = idUsuario;
        this.nomeUsuario = nome;
        this.senhaUsuario = senha;
        this.cpf = cpf;
        this.emailUsuario = email;
    }

    public Usuario(String nomeUsuario, String senhaUsuario, String cpf, String emailUsuario) {
        this.nomeUsuario = nomeUsuario;
        this.senhaUsuario = senhaUsuario;
        this.cpf = cpf;
        this.emailUsuario = emailUsuario;
    }

    public Integer getId() {
        return idUsuario;
    }

    public void setId(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenhaUsuario() {
        return senhaUsuario;
    }

    public void setSenhaUsuario(String senhaUsuario) {
        this.senhaUsuario = senhaUsuario;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }
}
