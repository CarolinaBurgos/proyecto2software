/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author Angel Moya
 */
public class Empleado extends Ciudadano{
    protected String userName;
    protected String permiso;
    protected String psswd;
    protected Date fecha_contratacion;
    protected Date fecha_actualizacion;
    protected boolean isActvo;

    public Empleado(  String id_entidad, String nombre, String apellido,String userName, String correo,String psswd,String permiso,Date fecha_contratacion,Date fecha_actualizacion,boolean isActivo) {
        super(id_entidad, nombre, apellido, correo);
        this.userName = userName;
        this.permiso = permiso;
        this.psswd = psswd;
        this.fecha_actualizacion=fecha_actualizacion;
        this.fecha_contratacion=fecha_contratacion;
        this.isActvo=isActivo;
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

    @Override
    public String toString() {
        return "Empleado{" + "userName=" + userName + ", permiso=" + permiso + ", psswd=" + psswd + ", fecha_contratacion=" + fecha_contratacion + ", fecha_actualizacion=" + fecha_actualizacion + ", isActvo=" + isActvo + '}';
    }
    
    
}
