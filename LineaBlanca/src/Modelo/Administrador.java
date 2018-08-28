/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Modelo.chainofresponsibility.Vendedor;
import java.util.Date;

/**
 *
 * @author Angel Moya
 */
public class Administrador extends Empleado{

    public Administrador(String id_entidad, String nombre, String apellido, String userName, String correo, String psswd, String permiso, Date fecha_contratacion, Date fecha_actualizacion, boolean isActivo) {
        super(id_entidad, nombre, apellido, userName, correo, psswd, permiso, fecha_contratacion, fecha_actualizacion, isActivo);
    }
    
    
    
    public Empleado agregarUsuario(){
        //INSERT CODE HERE
        return null;
    }
   
    public boolean aprobarAutorizacion(Vendedor vendedor){
        return true;
    }
    
    public Empleado modificarUsuario(){
        return null;
    }
    
    public Empleado eliminarUsuario(){
        return null;
    }
}
