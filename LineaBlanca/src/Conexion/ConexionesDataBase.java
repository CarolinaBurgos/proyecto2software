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
/**
 *
 * @author Carolina
 */
public class ConexionesDataBase {
   
    private static Connection conn;
    private static final String driver = "org.postgresql.Driver"; //driver (se queda siempre igual, si se usa postgres)
    private static final String usuario = "postgres"; //usuario de la base de datos
    private static final String contrasenna = "postgres"; //contraseña del usuario
    private static final String url = "jdbc:postgresql://192.168.0.9:5432/PruebaLineaBlanca";

    public Connection getConn() {
        return conn;
    }
    
    public static void conect(){
        

        try  {
            conn = DriverManager.getConnection(url, usuario, contrasenna); 
        } 

       catch (Exception e) {
           e.printStackTrace();
       }

    }
    
    public static void closeConnection(){
        try {
            if(!conn.isClosed())
                conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionesDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
