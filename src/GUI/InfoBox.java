/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javax.swing.JOptionPane;

/**
 *
 * @author Anders
 */
public class InfoBox {
    
    protected int messageType;
    protected String message;
    
    public void InfoBox(int messageType){
        
        this.messageType = messageType;
        
        switch(messageType){
            
            case 1: message = "You need to be Administrator, to use this function.";
                break;
            
            case 2: message = "Function Under Construction";
                break;
            
            case 3: message = "Contact the System Administrator";
                break;
            
        } 
        
        JOptionPane.showMessageDialog(null, message);
    }
    
}
