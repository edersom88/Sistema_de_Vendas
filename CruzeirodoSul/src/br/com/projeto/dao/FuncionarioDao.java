package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Funcionario;
import br.com.projeto.view.frmlogin;
import br.com.projeto.view.frmmenu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Edersom
 */
public class FuncionarioDao {
    //conexão automática com BD
    private final Connection con;

    public FuncionarioDao() {
        this.con = new ConnectionFactory().getConection();
    }
    
    //Metodo para cadastro de funcionários
    public void cadastrarFuncionario(Funcionario obj) {
        //será criado o comando sql para enviar os dados ao banco 
        try {
            //criação do comando sql
            String sql = "insert into tb_funcionarios (nome,rg,cpf,email,senha,cargo,nivel_acesso,telefone,celular,cep,endereco,numero,complemento,bairro,cidade,estado)"
                    + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            try ( //conexão ao BD e organizar o sql
                    PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setString(1, obj.getNome());
                stmt.setString(2, obj.getRg());
                stmt.setString(3, obj.getCpf());
                stmt.setString(4, obj.getEmail());
                stmt.setString(5, obj.getSenha());
                stmt.setString(6, obj.getCargo());
                stmt.setString(7, obj.getNivel_acesso());
                stmt.setString(8, obj.getTelefone());
                stmt.setString(9, obj.getCelular());
                stmt.setString(10, obj.getCep());
                stmt.setString(11, obj.getEndereco());
                stmt.setInt(12, obj.getNumero());
                stmt.setString(13, obj.getComplemento());
                stmt.setString(14, obj.getBairro());
                stmt.setString(15, obj.getCidade());
                stmt.setString(16, obj.getEstado());
                //Execução do comando
                stmt.execute();
            }

            JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso.");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
        }

    }
    
    //Metodo Excluir funcionario
      public void excluirFuncionario(Funcionario obj) {
         //será criado o comando sql para excluir os dados do banco 
        try {
            //criação do comando sql
            String sql = "delete from tb_funcionarios where id = ?";
            
            try ( //conexão ao BD e organizar o sql
                    PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setInt(1, obj.getId());
                //Execução do comando
                stmt.execute();
            }

            JOptionPane.showMessageDialog(null, "Excluído com Sucesso.");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
        }

    }
    //Metodo Alterar funcionário
    public void alterarFuncionario(Funcionario obj) {
        //será criado o comando sql para alterar os dados do banco 
        try {
            //criação do comando sql
            String sql = "update tb_funcionarios set nome=?,rg=?,cpf=?,email=?,senha=?, cargo=?,nivel_acesso=?,telefone=?,celular=?,cep=?,endereco=?,numero=?,"
                    +"complemento=?,bairro=?,cidade=?,estado=? where id=?";
            try ( //conexão ao BD e organizar o sql
                    PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setString(1, obj.getNome());
                stmt.setString(2, obj.getRg());
                stmt.setString(3, obj.getCpf());
                stmt.setString(4, obj.getEmail());
                stmt.setString(5, obj.getSenha());
                stmt.setString(6, obj.getCargo());
                stmt.setString(7, obj.getNivel_acesso());
                stmt.setString(8, obj.getTelefone());
                stmt.setString(9, obj.getCelular());
                stmt.setString(10, obj.getCep());
                stmt.setString(11, obj.getEndereco());
                stmt.setInt(12, obj.getNumero());
                stmt.setString(13, obj.getComplemento());
                stmt.setString(14, obj.getBairro());
                stmt.setString(15, obj.getCidade());
                stmt.setString(16, obj.getEstado());
                stmt.setInt(17, obj.getId());
                //Execução do comando
                stmt.execute();
            }

            JOptionPane.showMessageDialog(null, "Alterado com Sucesso.");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
        }
    }
    
    //Metodo listar funcionario
    public List<Funcionario> listarFuncionarios() {
        try {
            //criação da lista
            List<Funcionario> lista = new ArrayList<>();
            //Sql - organização e execução
            String sql = "select * from tb_funcionarios";
            PreparedStatement stmt = con.prepareStatement(sql);
            //vai executar o comando sql no java
            ResultSet rs = stmt.executeQuery();

            //adicionar os dados na lista do java
            //enquanto percorrer os dados vai preencher o objeto cliente
            while (rs.next()) {
                Funcionario obj = new Funcionario();
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setSenha(rs.getString("senha"));
                obj.setCargo(rs.getString("cargo"));
                obj.setNivel_acesso(rs.getString("nivel_acesso"));
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
    
  
    public Funcionario consultaFuncionarioPorCpf(String cpf) {
        try {
            //Sql - vai procurar pelo nome escrito na pesquisa
            String sql = "select * from tb_funcionarios where cpf = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, cpf);
            //vai executar o comando sql no java
            ResultSet rs = stmt.executeQuery();
            //enquanto percorrer os dados vai preencher o objeto cliente
            Funcionario obj = new Funcionario();
            
            if (rs.next()) {
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setSenha(rs.getString("senha"));
                obj.setCargo(rs.getString("cargo"));
                obj.setNivel_acesso(rs.getString("nivel_acesso"));
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
            JOptionPane.showMessageDialog(null, "Funcionario não encontrado!");
            return null;
        }
    }
    
    //Método buscar clientes por nome
    public List<Funcionario> buscarFuncionarioPorNome(String nome) {
        try {
            //criação da lista
            List<Funcionario> lista = new ArrayList<>();
            //Sql - vai procurar pelo nome escrito na pesquisa
            String sql = "select * from tb_funcionarios where nome like ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            //vai executar o comando sql no java
            ResultSet rs = stmt.executeQuery();

            //adicionar os dados na lista do java
            //enquanto percorrer os dados vai preencher o objeto cliente
            while (rs.next()) {
                Funcionario obj = new Funcionario();
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setSenha(rs.getString("senha"));
                obj.setCargo(rs.getString("cargo"));
                obj.setNivel_acesso(rs.getString("nivel_acesso"));
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
    
    //Método efetuar login do fucnionário
    public void acessarSistema(String cpf, String senha){
        try {
            //comando Sql
            String sql = "select * from tb_funcionarios where cpf = ? and senha = ?";
            //conexão ao banco
            PreparedStatement stmt = con.prepareStatement(sql);
            //organização dos dados
            stmt.setString(1, cpf);
            stmt.setString(2, senha);
            //recebe e retorna o comando sql no java
            ResultSet rs = stmt.executeQuery();
            
            //login
            if(rs.next()){
                //caso seja do tipo =Admin
                if(rs.getString("nivel_acesso").equals("Admin")){
                    JOptionPane.showMessageDialog(null, "Login efetuado!!");
                    //Abrir a tela principal
                    frmmenu tela = new frmmenu();
                    tela.acessousuario = rs.getString("nome");
                    tela.setVisible(true);
                }
                //Caso seja usuário normal
                else if(rs.getString("nivel_acesso").equals("Usuário")){
                    JOptionPane.showMessageDialog(null, "Login efetuado!!");
                    //Abrir a tela principal
                    frmmenu tela = new frmmenu();
                    tela.acessousuario = rs.getString("nome");
                    //Desabilitar Menus
                    tela.menuparciais.setEnabled(false);
                    tela.menuhistorico.setEnabled(false);
                    tela.menufuncionarios.setEnabled(false);
                    tela.setVisible(true);
                }
                
            } else{
                //dados incorretos
                JOptionPane.showMessageDialog(null, "Usuário e/ou senha incorretos!");
                new frmlogin().setVisible(true);
            }
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
        }
        
    }
    
}
