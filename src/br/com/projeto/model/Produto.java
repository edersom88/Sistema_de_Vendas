package br.com.projeto.model;

/**
 *
 * @author Edersom Denir Giaocmelli
 */
public class Produto {
    //Atributos
    private int id;
    private String descricao;
    private double preco;
    private int qtd_estoque;
    
    //utilização da chave estrangeira do fornecedor (id)
    //cria objeto para manipular informação
    private Fornecedor fornecedor;
    
    //Getters e Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQtd_estoque() {
        return qtd_estoque;
    }

    public void setQtd_estoque(int qtd_estoque) {
        this.qtd_estoque = qtd_estoque;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
   

}
