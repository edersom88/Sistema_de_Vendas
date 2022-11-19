/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Cliente;
import br.com.projeto.model.ItensVenda;
import br.com.projeto.model.Produto;
import br.com.projeto.model.Vendas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Edersom Denir Giacomelli
 */
public class ItemVendaDao {
     //conexão automática
    private Connection con;

    public ItemVendaDao() {
        this.con = new ConnectionFactory().getConection();
    }
    
    //Método de cadastro de itens
    public void cadastraItem(ItensVenda obj){
        try {
             //criar o sql para inserção no banco
            String sql = "INSERT INTO bdvendas.tb_itensvendas (venda_id, produto_id, qtd, subtotal) VALUES (?, ?, ?, ?);";
            //conexão ao banco e organização dos dados no sql
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getVenda().getId());
            stmt.setInt(2, obj.getProduto().getId());
            stmt.setDouble(3, obj.getQtd());
            stmt.setDouble(4, obj.getSubtotal());
            
            
            stmt.execute();
            stmt.close();
            
             
            
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null,"Erro: " + erro);
        }
    }
    
    //Método lista itens em uma venda por Id
    
     public List<ItensVenda> listarItensPorVenda(int venda_id){
        try {
            //criação da lista
        List<ItensVenda> lista = new ArrayList<>();
        
        //comando sql e organização dos dados
        String sql = "select p.descricao, i.qtd, p.preco, i.subtotal from tb_itensvendas as i inner join "
                    + " tb_produtos as p on (i.produto_id = p.id) where i.venda_id = ?";
        
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, venda_id);
        ResultSet rs = stmt.executeQuery();
        
        while(rs.next()){
            ItensVenda item = new ItensVenda();
            Produto produto = new Produto();
                        
            //item.setId(rs.getInt("i.id"));
            produto.setDescricao(rs.getString("p.descricao"));
            item.setQtd(rs.getInt("i.qtd"));
            produto.setPreco(rs.getDouble("p.preco"));
            item.setSubtotal(rs.getDouble("i.subtotal"));
            
            //passa os itens do produto para item
            item.setProduto(produto);
            
            lista.add(item);
            }
        return lista;
        
        } catch (SQLException erro) {
             JOptionPane.showMessageDialog(null, "Erro " + erro);
             return null;
        }
        
        
    }
     
     
}
