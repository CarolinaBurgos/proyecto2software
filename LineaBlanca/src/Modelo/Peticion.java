/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Objects;


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
    private String razon_cambio;
    
    
    public Peticion() {
        this.id=Integer.MAX_VALUE;
        this.id_empleado="";
        this.id_venta=Integer.MAX_VALUE;
        this.aprobacion_pendiente=true;
        this.peticion_aceptada=false;
        this.razon_cambio="";
        
    }

    public Peticion(int id, String id_empleado, int id_venta, boolean aprobacion_pendiente, boolean peticion_aceptada, String razon) {
        this.id = id;
        this.id_empleado = id_empleado;
        this.id_venta = id_venta;
        this.aprobacion_pendiente = aprobacion_pendiente;
        this.peticion_aceptada = peticion_aceptada;
        this.razon_cambio=razon;
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

    public String getRazon_cambio() {
        return razon_cambio;
    }

    public void setRazon_cambio(String razon_cambio) {
        this.razon_cambio = razon_cambio;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + this.id;
        hash = 47 * hash + Objects.hashCode(this.id_empleado);
        hash = 47 * hash + this.id_venta;
        hash = 47 * hash + (this.aprobacion_pendiente ? 1 : 0);
        hash = 47 * hash + (this.peticion_aceptada ? 1 : 0);
        hash = 47 * hash + Objects.hashCode(this.razon_cambio);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Peticion other = (Peticion) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.id_venta != other.id_venta) {
            return false;
        }
        if (this.aprobacion_pendiente != other.aprobacion_pendiente) {
            return false;
        }
        if (this.peticion_aceptada != other.peticion_aceptada) {
            return false;
        }
        if (!Objects.equals(this.id_empleado, other.id_empleado)) {
            return false;
        }
        if (!Objects.equals(this.razon_cambio, other.razon_cambio)) {
            return false;
        }
        return true;
    }

    
    
    @Override
    public String toString() {
        return  id + ", " + id_empleado + ", " + id_venta + ", " + aprobacion_pendiente + ", " + peticion_aceptada+", "+razon_cambio;
    }
    
    
    
    
}
