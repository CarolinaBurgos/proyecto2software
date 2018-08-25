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
public class Entidad {
    
    
    //atributos
    protected String id_entidad;
    protected String nombre;
    protected String correo;
    protected String telefono;
    protected String direccion;

    
    //constructores
    public Entidad(String id_entidad, String nombre, String correo, String telefono, String direccion) {
        this.id_entidad = id_entidad;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.direccion = direccion;
    }

   
    public Entidad(String id_entidad, String nombre, String correo) {
        this.id_entidad = id_entidad;
        this.nombre = nombre;
        this.correo = correo;
    }

    public Entidad() {
        id_entidad="";
        nombre="";
        correo="";
        telefono="";
        direccion="";
    }
    

    public String getId_entidad() {
        return id_entidad;
    }

    public void setId_entidad(String id_entidad) {
        this.id_entidad = id_entidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    //Para insercion directa en querys
    @Override
    public String toString() {
        return id_entidad + ", " + nombre + ", " + correo + ", " + telefono + "," + direccion;
    }
    
    
}
