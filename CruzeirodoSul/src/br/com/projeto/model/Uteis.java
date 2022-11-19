package br.com.projeto.model;

import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Edersom
 */
public class Uteis {
    
    //método usado para limpar campos gerais no projeto
    //percorre todos os campos e limpa cada um
    public void LimparTela(JPanel container){
        Component components[] = container.getComponents();
        for (Component component:components){
            if(component instanceof JTextField) {
            ((JTextField)component).setText(null);
        }
        } 
    }
    


}
