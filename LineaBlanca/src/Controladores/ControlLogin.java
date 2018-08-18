/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Conexion.ConexionesDataBase;
import java.sql.Connection;

/**
 *
 * @author Carolina
 */
public class ControlLogin  extends ConexionesDataBase{
    
    private Connection conn; 
    
    public ControlLogin(){
        
    }
    
    public void connectar(){
        ConexionesDataBase.conect();
        conn = super.getConn();    
    }
    
    
}
