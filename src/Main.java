import Controller.LoginController;
//import GUI.CalendarGUI;
import GUI.LoginGUI;
import Model.LoginModel;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Mal
 */
public class Main {
    public static void main(String[] args) {
       
       LoginGUI g = new LoginGUI();
       LoginModel m = new LoginModel();
       
       LoginController l = new LoginController(m, g);
       
    }
    
}
