package org.example.model;

public class Produto {

    private Integer idProduto;
    private String nomeProduto;
    private String descricaoProduto;
    private Double precoProduto;
    private Integer quantidadeProduto;
    private String tipoProduto;

    private int idFornecedor;

    private String nomeFornecedor;

    public Produto() {}


    public Produto(int idProduto, String nomeProduto, String descricaoProduto, Double precoProduto, Integer quantidadeProduto, String tipoProduto, int idFornecedor, String nomeFornecedor) {
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.descricaoProduto = descricaoProduto;
        this.precoProduto = precoProduto;
        this.quantidadeProduto = quantidadeProduto;
        this.tipoProduto = tipoProduto;
        this.idFornecedor = idFornecedor;
        this.nomeFornecedor = nomeFornecedor;
    }
    public Produto(int idProduto, String nomeProduto, String descricaoProduto, Double precoProduto, Integer quantidadeProduto, String tipoProduto, int idFornecedor) {
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.descricaoProduto = descricaoProduto;
        this.precoProduto = precoProduto;
        this.quantidadeProduto = quantidadeProduto;
        this.tipoProduto = tipoProduto;
        this.idFornecedor = idFornecedor;

    }



    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = String.valueOf(descricaoProduto);
    }

    public Double getPrecoProduto() {
        return precoProduto;
    }

    public void setPrecoProduto(Double precoProduto) {
        this.precoProduto = precoProduto;
    }

    public Integer getQuantidadeProduto() {
        return quantidadeProduto;
    }

    public void setQuantidadeProduto(Integer quantidadeProduto) {
        this.quantidadeProduto = quantidadeProduto;
    }

    public String getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(String tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    public int getIdFornecedor() {
        return idFornecedor;
    }
    public void setIdFornecedor(int idFornecedor) {
        this.idFornecedor = idFornecedor;
    }
    public String getNomeFornecedor() {
        return nomeFornecedor;
    }
    public void setNomeFornecedor(String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
    }



}
