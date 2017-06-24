/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            String seleccionar = "select * from clientes where id_cliente = ?";
            PreparedStatement ps = c.prepareStatement(seleccionar); //clase
            String in = "Insert into clientes (nombre, apellido) values(?,?)";
            PreparedStatement psi = c.prepareStatement(in);
            String updt = "UPDATE clientes SET nombre = ?, apellido = ? WHERE id_cliente = ?";
            PreparedStatement psa =c.prepareStatement(updt);
            String del = "DELETE FROM clientes WHERE id_cliente = ?";
            PreparedStatement pse = c.prepareStatement(del);
           
            //insertar(psi, "Juan", "Lopez");
            //insertar(psi, "Diego", "Ramos");
            
            getById(ps, 1);
            getById(ps, 7);
            getById(ps, 9);
            
            actualizar(psa, 9, "Hector", "Gomez");
            getById(ps, 1);
            
            //eliminar(pse, 8);
            
            getById(ps, 1);
        }
        catch (SQLException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void getById(PreparedStatement ps, int id_param) throws SQLException {
            ps.setInt(1, id_param);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                int id = rs.getInt("id_cliente");
                String nom = rs.getString("nombre");
                String ap = rs.getString("apellido");
                System.out.println(id + " " + nom + " " + ap);
            }
    }
    
    private static void insertar (PreparedStatement psi, String nombre, String apellido) throws SQLException{
       psi.setString(1, nombre);
       psi.setString(2, apellido);
       psi.execute();
    }
    
    private static void actualizar (PreparedStatement psa, int id, String nombre, String apellido) throws SQLException{
        psa.setString(1, nombre);
        psa.setString(2, apellido);
        psa.setInt(3, id);
        psa.execute();
    }
    
    private static void eliminar (PreparedStatement pse, int id)throws SQLException{
        pse.setInt(1, id);
        pse.execute();
    }
}     
