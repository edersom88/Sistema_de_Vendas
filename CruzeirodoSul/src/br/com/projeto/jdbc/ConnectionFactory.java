/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author edoom
 */
public class ConnectionFactory {
    ////metodo retorna objeto do tipo connection (conecta ao banco)
    //Connection está na biblioteca
    public Connection getConection(){
        //esse método precisa de tratamento de erro
        try {
            //informação necessária para a conexão (infos do BD)
            return DriverManager.getConnection("jdbc:mysql://127.0.0.1/bdvendas","usuario","123");
        } 
        //caso ocorram erros
        catch (SQLException erro) { 
            //vai mostrar o que houve de erro
            throw new RuntimeException(erro);
        }
    }
    
}
