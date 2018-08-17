/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;
import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author Carolina
 */
public class ConexionesDataBase {
   
    
    public static void conect(){
        String userName = "postgres";
        String password = "postgres";
        String url = "jdbc:postgresql://192.168.0.9:5432/PruebaLineaBlanca";

        try  {
            Connection conn = DriverManager.getConnection(url, userName, password); 
            System.out.println("Surprise mudafaka");
        } 

       catch (Exception e) {
           e.printStackTrace();
       }

    }
}
