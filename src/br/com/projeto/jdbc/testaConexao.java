/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.jdbc;

import javax.swing.JOptionPane;

/**
 *
 * @author edoom
 */
public class testaConexao {
    public static void main(String[] args) {
        try {
            new ConnectionFactory().getConection();
            JOptionPane.showMessageDialog(null, "conectado com sucesso");
        } catch (Exception erro) {
             JOptionPane.showMessageDialog(null, "Falha na conexão, erro: " + erro);
        }
    }
}
