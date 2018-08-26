/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;


/**
 *
 * @author Toshiba
 */
public class Peticion {
    //atributos
    
    private int id;
    private String id_empleado;
    private int id_venta;
    private boolean aprobacion_pendiente;
    private boolean peticion_aceptada;
    
    
    public Peticion() {
        this.id=Integer.MAX_VALUE;
        this.id_empleado="";
        this.id_venta=Integer.MAX_VALUE;
        this.aprobacion_pendiente=true;
        this.peticion_aceptada=false;
        
    }

    public Peticion(int id, String id_empleado, int id_venta, boolean aprobacion_pendiente, boolean peticion_aceptada) {
        this.id = id;
        this.id_empleado = id_empleado;
        this.id_venta = id_venta;
        this.aprobacion_pendiente = aprobacion_pendiente;
        this.peticion_aceptada = peticion_aceptada;
    }

    public boolean isPeticion_aceptada() {
        return peticion_aceptada;
    }

    public void setPeticion_aceptada(boolean peticion_aceptada) {
        this.peticion_aceptada = peticion_aceptada;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(String id_empleado) {
        this.id_empleado = id_empleado;
    }

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    public boolean isAprobacion_pendiente() {
        return aprobacion_pendiente;
    }

    public void setAprobacion_pendiente(boolean aprobacion_pendiente) {
        this.aprobacion_pendiente = aprobacion_pendiente;
    }

    @Override
    public String toString() {
        return  id + ", " + id_empleado + ", " + id_venta + ", " + aprobacion_pendiente + ", " + peticion_aceptada;
    }
    
    
    
    
}
