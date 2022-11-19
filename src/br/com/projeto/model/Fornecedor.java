/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.model;

/**
 *
 * @author edoom
 */
public class Fornecedor extends Cliente{
    //atributos extras
    private String cnpj;
    
    
    //Getters e Setters

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    
    //para inserir o nome do fornecedor no combo box
    @Override
    public String toString(){
        return this.getNome();
    }

}