/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Fornecedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author edoom
 */
public class FornecedorDao {
    private Connection con;

    public FornecedorDao() {
        this.con = new ConnectionFactory().getConection();
    }
    
     //metodo de cadastro de fornecedor
    public void cadastrarFornecedor(Fornecedor obj) {
        //será criado o comando sql para enviar os dados ao banco 
        try {
            //criação do comando sql
            String sql = "insert into tb_fornecedores (nome,cnpj,email,telefone,celular,cep,endereco,numero,complemento,bairro,cidade,estado)"
                    + "values (?,?,?,?,?,?,?,?,?,?,?,?)";
            //conexão ao BD e organizar o sql
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getCnpj());
            stmt.setString(3, obj.getEmail());
            stmt.setString(4, obj.getTelefone());
            stmt.setString(5, obj.getCelular());
            stmt.setString(6, obj.getCep());
            stmt.setString(7, obj.getEndereco());
            stmt.setInt(8, obj.getNumero());
            stmt.setString(9, obj.getComplemento());
            stmt.setString(10, obj.getBairro());
            stmt.setString(11, obj.getCidade());
            stmt.setString(12, obj.getEstado());
            //Execução do comando
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso.");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
        }

    }
    
     //metodo de exclusão de fornecedor
    public void excluirFornecedor(Fornecedor obj) {
         //será criado o comando sql para excluir os dados do banco 
        try {
            //criação do comando sql
            String sql = "delete from tb_fornecedores where id = ?";
            
            //conexão ao BD e organizar o sql
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getId());
            //Execução do comando
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Excluído com Sucesso.");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
        }

    }
    
    //metodo de alteração de cliente
    public void alterarFornecedor(Fornecedor obj) {
        //será criado o comando sql para alterar os dados do banco 
        try {
            //criação do comando sql
            String sql = "update tb_fornecedores set nome=?,cnpj=?,email=?,telefone=?,celular=?,cep=?,endereco=?,numero=?,"
                    +"complemento=?,bairro=?,cidade=?,estado=? where id=?";
            //conexão ao BD e organizar o sql
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getCnpj());
            stmt.setString(3, obj.getEmail());
            stmt.setString(4, obj.getTelefone());
            stmt.setString(5, obj.getCelular());
            stmt.setString(6, obj.getCep());
            stmt.setString(7, obj.getEndereco());
            stmt.setInt(8, obj.getNumero());
            stmt.setString(9, obj.getComplemento());
            stmt.setString(10, obj.getBairro());
            stmt.setString(11, obj.getCidade());
            stmt.setString(12, obj.getEstado());
            stmt.setInt(13, obj.getId());
            //Execução do comando
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Alterado com Sucesso.");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
        }
    }
    
    //método para listagem de Fornecedores
    //Necessário criar um array para lista de Fornecedores
    public List<Fornecedor> listarFornecedores() {
        try {
            //criação da lista
            List<Fornecedor> lista = new ArrayList<>();
            //Sql - organização e execução
            String sql = "select * from tb_fornecedores";
            PreparedStatement stmt = con.prepareStatement(sql);
            //vai executar o comando sql no java
            ResultSet rs = stmt.executeQuery();

            //adicionar os dados na lista do java
            //enquanto percorrer os dados vai preencher o objeto fornecedor
            while (rs.next()) {
                Fornecedor obj = new Fornecedor();
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCnpj(rs.getString("cnpj"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setEstado(rs.getString("estado"));
                //adicionar na lista
                lista.add(obj);
            }
            return lista;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro " + erro);
            return null;
        }
    }
    
    //método para listagem de Fornecedores
    //Necessário criar um array para lista de Fornecedores
    public List<Fornecedor> buscarFornecedorPorNome(String nome) {
        try {
            //criação da lista
            List<Fornecedor> lista = new ArrayList<>();
            //Sql - vai procurar pelo nome escrito na pesquisa
            String sql = "select * from tb_fornecedores where nome like ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            //vai executar o comando sql no java
            ResultSet rs = stmt.executeQuery();

            //adicionar os dados na lista do java
            //enquanto percorrer os dados vai preencher o objeto cliente
            while (rs.next()) {
                Fornecedor obj = new Fornecedor();
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCnpj(rs.getString("cnpj"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setEstado(rs.getString("estado"));
                //adicionar na lista
                lista.add(obj);
            }
            return lista;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro " + erro);
            return null;
        }
    }
    
    //Método de consulta por Cnpj
    public Fornecedor consultarPorCnpj(String cnpj) {
        try {
            //Sql - vai procurar pelo nome escrito na pesquisa
            String sql = "select * from tb_fornecedores where cnpj = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, cnpj);
            //vai executar o comando sql no java
            ResultSet rs = stmt.executeQuery();
            //enquanto percorrer os dados vai preencher o objeto cliente
            Fornecedor obj = new Fornecedor();
            
            if (rs.next()) {
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCnpj(rs.getString("cnpj"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setEstado(rs.getString("estado"));
               
            }
            return obj;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Fornecedor não encontrado!");
            return null;
        }
    }
    //Método de consulta por nome - para utilizar no produto
    public Fornecedor consultarPorNome(String nome) {
        try {
            //Sql - vai procurar pelo nome escrito na pesquisa
            String sql = "select * from tb_fornecedores where nome = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            //vai executar o comando sql no java
            ResultSet rs = stmt.executeQuery();
            //enquanto percorrer os dados vai preencher o objeto cliente
            Fornecedor obj = new Fornecedor();
            
            if (rs.next()) {
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCnpj(rs.getString("cnpj"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setEstado(rs.getString("estado"));
               
            }
            return obj;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Fornecedor não encontrado!");
            return null;
        }
    }
}
