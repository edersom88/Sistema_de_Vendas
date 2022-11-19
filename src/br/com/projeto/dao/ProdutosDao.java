package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Fornecedor;
import br.com.projeto.model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Edesom Denir Giacomelli
 */
public class ProdutosDao {
    //conexão automática
    private Connection con;

    public ProdutosDao() {
        this.con = new ConnectionFactory().getConection();
    }
    
    //Método para cadastrar os produtos
    public void cadastrar(Produto obj){
        try {
            //criar o sql para inserção no banco
            String sql = "insert into tb_produtos (descricao, preco, qtd_estoque, for_id) values (?,?,?,?)";
            //conexão ao banco e organização dos dados no sql
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getDescricao());
            stmt.setDouble(2, obj.getPreco());
            stmt.setInt(3, obj.getQtd_estoque());
            stmt.setInt(4, obj.getFornecedor().getId());
            
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null,"Produto cadastrado.");
            
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null,"Erro: " + erro);
        }
    }
    
    public void alterar(Produto obj){
        try {
            //criar o sql para alteração no banco
            String sql = "update tb_produtos set descricao = ?, preco = ?, qtd_estoque = ?, for_id = ? where id=?";
            //conexão ao banco e organização dos dados no sql
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getDescricao());
            stmt.setDouble(2, obj.getPreco());
            stmt.setInt(3, obj.getQtd_estoque());
            stmt.setInt(4, obj.getFornecedor().getId());
            
            stmt.setInt(5, obj.getId());
            
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null,"Dados alterados com sucesso.");
            
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null,"Erro: " + erro);
        }
    }
    
    public void excluir(Produto obj){
        try {
            //criar o sql para exclusão no banco
            String sql = "delete from tb_produtos where id=?";
            //conexão ao banco e organização dos dados no sql
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setInt(1, obj.getId());
            
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null,"Dados excluídos com sucesso.");
            
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null,"Erro: " + erro);
        }
    }
    
    public List<Produto> listarProdutos(){
        try {
            //criação da lista
        List<Produto> lista = new ArrayList<>();
        
        //comando sql e organização dos dados
        String sql = "select p.id, p.descricao, p.preco, p.qtd_estoque, f.nome from "
                + "tb_produtos as p inner join tb_fornecedores as f on (p.for_id = f.id)";
        
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        
        while(rs.next()){
            Produto obj = new Produto();
            Fornecedor f = new Fornecedor();
            
            obj.setId(rs.getInt("p.id"));
            obj.setDescricao(rs.getString("p.descricao"));
            obj.setPreco(rs.getDouble("p.preco"));
            obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));
            
            f.setNome(rs.getString("f.nome"));
            
            obj.setFornecedor(f);
            
            lista.add(obj);
            }
        return lista;
        
        } catch (SQLException erro) {
             JOptionPane.showMessageDialog(null, "Erro " + erro);
             return null;
        }
        
        
    }
    
     public List<Produto> listarProdutosPorNome(String nome){
        try {
            //criação da lista
        List<Produto> lista = new ArrayList<>();
        
        //comando sql e organização dos dados
        String sql = "select p.id, p.descricao, p.preco, p.qtd_estoque, f.nome from "
                + " tb_produtos as p inner join tb_fornecedores as f on (p.for_id = f.id) "
                +" where p.descricao like ?";
        
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, nome);
        ResultSet rs = stmt.executeQuery();
        
        while(rs.next()){
            Produto obj = new Produto();
            Fornecedor f = new Fornecedor();
            
            obj.setId(rs.getInt("p.id"));
            obj.setDescricao(rs.getString("p.descricao"));
            obj.setPreco(rs.getDouble("p.preco"));
            obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));
            
            f.setNome(rs.getString("f.nome"));
            
            obj.setFornecedor(f);
            
            lista.add(obj);
            }
        return lista;
        
        } catch (SQLException erro) {
             JOptionPane.showMessageDialog(null, "Erro " + erro);
             return null;
        }
        
        
    }
     
      public Produto consultarProdutosPorNome(String nome){
        try {
        //comando sql e organização dos dados
        String sql = "select p.id, p.descricao, p.preco, p.qtd_estoque, f.nome from "
                + " tb_produtos as p inner join tb_fornecedores as f on (p.for_id = f.id) "
                +" where p.descricao = ?";
        
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, nome);
        
        ResultSet rs = stmt.executeQuery();
        
        Produto obj = new Produto();
        Fornecedor f = new Fornecedor();
        
        if(rs.next()){
            
            obj.setId(rs.getInt("p.id"));
            obj.setDescricao(rs.getString("p.descricao"));
            obj.setPreco(rs.getDouble("p.preco"));
            obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));
            
            f.setNome(rs.getString("f.nome"));
            
            obj.setFornecedor(f);
            
            }
        return obj;
        
        } catch (Exception erro) {
             JOptionPane.showMessageDialog(null, "Produto não encontrado");
             return null;
        }
        
        
    }
      
      public Produto consultarProdutosPorCodigo(String codigo){
        try {
        //comando sql e organização dos dados
        String sql = "select p.id, p.descricao, p.preco, p.qtd_estoque, f.nome from "
                + " tb_produtos as p inner join tb_fornecedores as f on (p.for_id = f.id) "
                +" where p.id = ?";
        
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, codigo);
        
        ResultSet rs = stmt.executeQuery();
        
        Produto obj = new Produto();
        Fornecedor f = new Fornecedor();
        
        if(rs.next()){
            
            obj.setId(rs.getInt("p.id"));
            obj.setDescricao(rs.getString("p.descricao"));
            obj.setPreco(rs.getDouble("p.preco"));
            obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));
            
            f.setNome(rs.getString("f.nome"));
            
            obj.setFornecedor(f);
            
            }
        return obj;
        
        } catch (Exception erro) {
             JOptionPane.showMessageDialog(null, "Produto não encontrado");
             return null;
        }
        
        
    }
      
      //Método de baixa no estoque
      public void baixarEstoque (int id, int qtd_nova) {
          try {
              String sql = "update tb_produtos set qtd_estoque = ? where id=?";
              
              PreparedStatement stmt = con.prepareStatement(sql);
              
              stmt.setInt(1, qtd_nova);
              stmt.setInt(2, id);
              
              stmt.execute();
              stmt.close();
              
              
          } catch (Exception erro) {
              JOptionPane.showMessageDialog(null, "Erro " + erro);
          }
      }
      
      //Método para retornar o estoque atual - consulta
      public int retornarEstoque(int id){
          try {
              int qtd_estoque = 0;
              
              String sql = "Select qtd_estoque from tb_produtos where id = ?";
              
              PreparedStatement stmt = con.prepareStatement(sql);
              stmt.setInt(1,id);
              
              ResultSet rs = stmt.executeQuery();
              
              if(rs.next()){

                  qtd_estoque = (rs.getInt("qtd_estoque"));
              
              }
              return qtd_estoque;
              
          } catch (SQLException erro) {
              throw new RuntimeException(erro);
          }
      }
      
       //Método de baixa no estoque
      public void adicionarEstoque (int id, int qtd_nova) {
          try {
              String sql = "update tb_produtos set qtd_estoque = ? where id=?";
              
              PreparedStatement stmt = con.prepareStatement(sql);
              
              stmt.setInt(1, qtd_nova);
              stmt.setInt(2, id);
              
              stmt.execute();
              stmt.close();
              
              
          } catch (Exception erro) {
              JOptionPane.showMessageDialog(null, "Erro " + erro);
          }
      }
    
}
