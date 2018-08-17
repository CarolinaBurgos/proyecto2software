/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author joset
 */

//Aqui haremos la conexion a la base de datos
class Conexion{
    //la conexion propiamente dicha
    private static Connection conn;
    /*
    Se deben especificar esta informacion que detalla el usuario, la contraseña y la informacion del servidor (ip , puerto, y nombre de la base de datos).
    
    */
    
    
    private static final String driver = "org.postgresql.Driver"; //driver (se queda siempre igual, si se usa postgres)
    private static final String usuario = "postgres"; //usuario de la base de datos
    private static final String contrasenna = "postgres"; //contraseña del usuario
    private static final String url = "jdbc:postgresql://190.130.200.169:5432/PruebaLineaBlanca"; //basicamnete es la informacion del servidor de la base deatos y es jdbc:mysql://(direccionIp):(puerto)/(nombreDeLaBaseDeDatos)
    
    
    /*
    Este metodo es el constructor del metodo Conexion y se hace de esta forma normalmente
    
    */
    
    public Conexion(){
        conn =null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, usuario, contrasenna);
            if ( conn != null){
                System.out.println("Se ha realizado una conexion exitosa!"); //debemos reemplazar esto por alertas genericas en el gui
            }
        }catch( ClassNotFoundException | SQLException e){
            System.out.println("Ha sucecido un problema");
            e.printStackTrace();
        }
    }
    
    //Obtiene el objeto Connection que es el que se instancia para hacer los querys
    public Connection getConnection(){
        return this.conn;
    }
    
    //cierra la conexion con la base de datos 
    public void cerrarConexion() throws SQLException{
        conn.close();
    }
    
    
}

