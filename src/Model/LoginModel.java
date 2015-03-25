
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.SQLException;


public class LoginModel extends DatabaseConn {

    public LoginModel() {
        super();
    }
    /*
    @author Anders, Malthe
    Beskrivelse: Checker om passwordet stemmer overens med det givne brugernavn
    ved at returnere en boolean. Kaldes af loginController's actionperformed
    */
    public boolean login(String username, String password) {

        try {
            String prep_statement = "SELECT * FROM Users WHERE username = ? AND password = MD5(?) ";
            java.sql.PreparedStatement stmn = this.conn.prepareStatement(prep_statement);
            //Sætter '?' linjerne i statement. 1. '?' er username 2. '?' er password.
            stmn.setString(1, username);
            stmn.setString(2, password);
            System.out.println("Hallo! " + username + prep_statement);
            rs = stmn.executeQuery();

            if (rs.next()) {
                String pass = rs.getString("password");

                System.out.println(pass);

                System.out.println("Welcome!! ");
                return true;

            } else {
                System.out.println("Username " + username + " does not exist");
                return false;
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("Username Does not exist");
            e.printStackTrace();
        }
        return false;
    }
    
    

}
