package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Cliente;
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
    
    //Método filtrar vendas por data
    public List<Vendas> listarVendasPorDatas(LocalDate data_inicio, LocalDate data_fim){
        try {
            //criação da lista
        List<Vendas> lista = new ArrayList<>();
        
        //comando sql e organização dos dados
        String sql = "select v.id, date_format(v.data_venda,'%d/%m/%Y') as data_formatada, c.nome, v.total_venda, v.observacoes from "
                + " tb_vendas as v inner join tb_clientes as c on (v.cliente_id = c.id) where v.data_venda BETWEEN ? AND ?";
        
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, data_inicio.toString());
        stmt.setString(2, data_fim.toString());
        ResultSet rs = stmt.executeQuery();
        
        while(rs.next()){
            Vendas venda = new Vendas();
            Cliente cliente = new Cliente();
            
            venda.setId(rs.getInt("v.id"));
            venda.setData_venda(rs.getString("data_formatada"));
            cliente.setNome(rs.getString("c.nome"));
            venda.setTotal_venda(rs.getDouble("v.total_venda"));
            venda.setObs(rs.getString("v.observacoes"));
            
            venda.setCliente(cliente);
            
            lista.add(venda);
            }
        return lista;
        
        } catch (SQLException erro) {
             JOptionPane.showMessageDialog(null, "Erro " + erro);
             return null;
        }
        
        
    }
    
    //Método de cálculo do valor vendido no dia
    
    public double retornaValorVendido(LocalDate data_venda){
        try {
            double totalvenda = 0;
            
            String sql = "select sum(total_venda) as total from tb_vendas where data_venda = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, data_venda.toString());
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()){
                totalvenda = rs.getDouble("total");
            }
            return totalvenda;
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }
    
}
