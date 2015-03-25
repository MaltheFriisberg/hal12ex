/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ande009
 */
public class DatabaseConn {
    
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://sql.abegrotten.org:3306/test";

    //  Database credentials
    static final String USER = "anders";
    static final String PASS = "ABCD#1234";

    protected Connection conn = null;
    protected Statement stmt = null;
    protected ResultSet rs = null;
    
    public DatabaseConn() 
    {

        try {
                Class.forName("com.mysql.jdbc.Driver");

                //System.out.println("Connecting to database...");
                conn = DriverManager.getConnection(DB_URL,USER,PASS);
                //System.out.println("Connected...");

                //System.out.println("Creating statement...");
                stmt = conn.createStatement();

        } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();

        } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }

            //System.out.println("Goodbye!");
    }        
        
    
}
