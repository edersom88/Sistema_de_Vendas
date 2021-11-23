package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Vendas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Edersom Denir Giacomelli
 */
public class VendasDao {
    //conexão automática
    private Connection con;

    public VendasDao() {
        this.con = new ConnectionFactory().getConection();
    }
    
    //Cadastro de Venda
    public void cadastroDeVenda(Vendas obj){
            try {
             //criar o sql para inserção no banco
            String sql = "INSERT INTO bdvendas.tb_vendas (cliente_id, data_venda, total_venda, observacoes) VALUES (?, ?, ?, ?);";
            //conexão ao banco e organização dos dados no sql
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getCliente().getId());
            stmt.setString(2, obj.getData_venda());
            stmt.setDouble(3, obj.getTotal_venda());
            stmt.setString(4, obj.getObs());
            
            
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null,"Venda registrada.");
            
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null,"Erro: " + erro);
        }
}
    
    
    //Retornar à última venda
    public int retornaVenda(){
        try {
            int idvenda = 0;
            
            //campo máximo do sql é retornado, ou seja, a última id de venda
            String sql = "Select max(id) id from tb_vendas";
            PreparedStatement ps = con.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()){
            Vendas p = new Vendas();
            
            p.setId(rs.getInt("id"));
            idvenda = p.getId();
            
            }
            return idvenda;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}
