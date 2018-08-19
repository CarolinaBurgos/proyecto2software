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
public class ControlVendedor extends ConexionesDataBase{
    
        protected Connection conexion; 
    
    public ControlVendedor(){
        
    }
    
    public void connectar(){
        ConexionesDataBase.conect();
        conexion = super.getConn();    
    }
    
}
