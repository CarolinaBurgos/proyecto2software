/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.logging.Level;
import java.util.logging.Logger;

import Exceptions.Entity.InvalidEntityIDException;
/**
 *
 * @author Angel Moya
 */
public class Ciudadano extends Entidad implements Cliente{

    public Ciudadano() {
        super();
    }
    
    
    public Ciudadano(String id_entidad, String nombre, String correo, String telefono, String direccion) {
        super(id_entidad, nombre, correo, telefono, direccion);
        
    }

    public Ciudadano(String id_entidad, String nombre,String apellido, String correo) {
        super(id_entidad, nombre, correo);
        
    }
     

    @Override
    public String getId_entidad() {
        return id_entidad;
    }

    @Override
    public void setId_entidad(String id_entidad) {
        
        if (id_entidad.length()==10)this.id_entidad = id_entidad;
        else try {
            throw new InvalidEntityIDException("Número de ID no válido");
        } catch (InvalidEntityIDException ex) {
            Logger.getLogger(Ciudadano.class.getName()).log(Level.WARNING, null, ex);
        }
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String getCorreo() {
        return correo;
    }

    @Override
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String getTelefono() {
        return telefono;
    }

    @Override
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String getDireccion() {
        return direccion;
    }

    @Override
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public boolean validarID() {
        if (this.id_entidad.length()==10) return true;
        else return false;
    }
    
    
    
}
