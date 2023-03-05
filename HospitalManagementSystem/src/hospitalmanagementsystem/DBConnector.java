/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hospitalmanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dinith
 */
public class DBConnector {
    
    public String user = "root";
    public String pass = "1234";
    public String conString = "jdbc:mysql://localhost:3306/mydb";
    
    private Connection con = null;
    
    public void getConnection(){
        try {
            con = DriverManager.getConnection(conString,user, pass);
        } catch (SQLException ex){
            System.out.println(ex);
        }
    }
    
    Connection giveConnection(){
        
        getConnection();
    
        return con;
        
    }
    
    public void checkConnection(){
        
        if(con != null){
        
            System.out.println("Connection success");
            
        }else{
            
            System.out.println("not ok");
            
        }
        
    }
   
    
}
