/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Toshiba
 */
public class CompraCliente {
    
    private int id_compra;
    private String tipo_comprobante_venta;
    private Date fecha_compra;
    private double monto;
    private Entidad cliente; 
    private Entidad empleado;

    public CompraCliente() {
    }


    public int getId_compra() {
        return id_compra;
    }

    public void setId_compra(int id_compra) {
        this.id_compra = id_compra;
    }

    public String getTipo_comprobante_venta() {
        return tipo_comprobante_venta;
    }

    public void setTipo_comprobante_venta(String tipo_comprobante_venta) {
        this.tipo_comprobante_venta = tipo_comprobante_venta;
    }

    public Date getFecha_compra() {
        return fecha_compra;
    }

    public void setFecha_compra(Date fecha_compra) {
        this.fecha_compra = fecha_compra;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Entidad getCliente() {
        return cliente;
    }

    public void setCliente(Entidad cliente) {
        this.cliente = cliente;
    }

    public Entidad getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Entidad empleado) {
        this.empleado = empleado;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + this.id_compra;
        hash = 43 * hash + Objects.hashCode(this.tipo_comprobante_venta);
        hash = 43 * hash + Objects.hashCode(this.fecha_compra);
        hash = 43 * hash + (int) (Double.doubleToLongBits(this.monto) ^ (Double.doubleToLongBits(this.monto) >>> 32));
        hash = 43 * hash + Objects.hashCode(this.cliente);
        hash = 43 * hash + Objects.hashCode(this.empleado);
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
        final CompraCliente other = (CompraCliente) obj;
        if (this.id_compra != other.id_compra) {
            return false;
        }
        if (Double.doubleToLongBits(this.monto) != Double.doubleToLongBits(other.monto)) {
            return false;
        }
        if (!Objects.equals(this.tipo_comprobante_venta, other.tipo_comprobante_venta)) {
            return false;
        }
        if (!Objects.equals(this.fecha_compra, other.fecha_compra)) {
            return false;
        }
        if (!Objects.equals(this.cliente, other.cliente)) {
            return false;
        }
        if (!Objects.equals(this.empleado, other.empleado)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return id_compra + ", " + tipo_comprobante_venta + ", " + fecha_compra +
                ", " + monto + ", " + cliente + ", " + empleado ;        
    }
    
    
    
    
    
    
    
    
    
}
