/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import javax.swing.JOptionPane;

/**
 *
 * @author Carlos Valdez
 */

public class Conexion{

    public Connection conectar(){ // Uso tradicional de conexionBD
        Connection con;
        try{
           Class.forName("com.mysql.jdbc.Driver");
           String url = "jdbc:mysql://127.0.0.1:3306/fincalab?autoReconnect=true&useSSL=false";
           con = DriverManager.getConnection(url, "root", "root");
           //JOptionPane.showMessageDialog(null,"Conexión establecida");
        }
       
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Error en Conexion de BD "+ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            con=null;
        }
        return con;
    }
}