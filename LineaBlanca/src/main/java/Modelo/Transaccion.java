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
public class Transaccion {
    private Date Fecha;
    private Empleado responsable;

    public Transaccion(Date Fecha, Empleado responsable) {
        this.Fecha = Fecha;
        this.responsable = responsable;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date Fecha) {
        this.Fecha = Fecha;
    }

    public Empleado getResponsable() {
        return responsable;
    }

    public void setResponsable(Empleado responsable) {
        this.responsable = responsable;
    }
    
    
}
