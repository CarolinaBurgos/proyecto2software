/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author MNLL
 */
public class ReporteVendedor extends Reporte{
    private String id, nombre;
    private float cantventas, monto;
    
    public ReporteVendedor(String id, String nombre, float cant, float monto){
        this.id = id;
        this.nombre=nombre;
        this.cantventas=cant;
        this.monto=monto;
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

    public float getCantventas() {
        return cantventas;
    }

    public void setCantventas(float cantventas) {
        this.cantventas = cantventas;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    @Override
    public String toString() {
        return "ReporteVendedor{" + "id=" + id + ", nombre=" + nombre + ", cantventas=" + cantventas + ", monto=" + monto + '}';
    }
    
    
}
