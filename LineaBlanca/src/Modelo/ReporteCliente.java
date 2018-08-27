/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author user
 */
public class ReporteCliente extends Reporte{
    private String id, nombre, direccion, telefono;
    private float monot;
    
    public ReporteCliente(String id, String nombre, String direccion, String fono,
    float med){
        this.direccion = direccion;
        this.id = id;
        this.nombre = nombre;
        this.telefono = fono;
        this.monot = med;
        
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public float getMonot() {
        return monot;
    }

    public void setMonot(float monot) {
        this.monot = monot;
    }

    @Override
    public String toString() {
        return "ReporteCliente{" + "id=" + id + ", nombre=" + nombre + ", direccion=" + direccion + ", telefono=" + telefono + ", monot=" + monot + '}';
    }
    
    
    
    
    
}
