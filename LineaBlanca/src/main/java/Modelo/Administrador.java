/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Angel Moya
 */
public class Administrador extends Empleado{
    
    public Administrador(String id_entidad, String nombre, String correo, String telefono, String direccion) {
        super(id_entidad, nombre, correo, telefono, direccion);
    }

    public Administrador(String userName, String permiso, String psswd, String apellido, String id_entidad, String nombre, String correo, String telefono, String direccion) {
        super(userName, permiso, psswd, apellido, id_entidad, nombre, correo, telefono, direccion);
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
