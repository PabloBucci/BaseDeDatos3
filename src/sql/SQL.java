/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author educacionit
 */
public class SQL {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) {
        try {
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/curso1", "root", "");
            String q = "select * from clientes";
            Statement stm = c.createStatement();
            ResultSet rs = stm.executeQuery(q);
            while (rs.next()){
                Integer id = rs.getInt("id_cliente");
                String nom = rs.getString("nombre");
                String ap = rs.getString("apellido");
                System.out.println(id + " " + nom + " " + ap);
            }
        
        }
        catch (SQLException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}     
