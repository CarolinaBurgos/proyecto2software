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
public class Empleado extends Ciudadano{
    private String userName;
    private String permiso;
    private String psswd;

    public Empleado(String id_entidad, String nombre, String correo, String telefono, String direccion) {
        super(id_entidad, nombre, correo, telefono, direccion);
    }

    public Empleado(String apellido, String id_entidad, String nombre, String correo, String telefono, String direccion) {
        super(apellido, id_entidad, nombre, correo, telefono, direccion);
    }

    
    
    public String identificarCargo(){
        //INSERT CODE
        return null;
    }
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPermiso() {
        return permiso;
    }

    public void setPermiso(String permiso) {
        this.permiso = permiso;
    }

    public String getPsswd() {
        return psswd;
    }

    public void setPsswd(String psswd) {
        this.psswd = psswd;
    }
    
    
}
