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
public class ReporteArticulo extends Reporte {
    
    private String id, articulo;
    private float cantidad, ventas;
    
    public ReporteArticulo(String id, String art, float cant, float ventas){
        this.id = id;
        this.articulo = art;
        this.cantidad = cant;
        this.ventas = ventas;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArticulo() {
        return articulo;
    }

    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public float getVentas() {
        return ventas;
    }

    public void setVentas(float ventas) {
        this.ventas = ventas;
    }

    @Override
    public String toString() {
        return "ReporteArticulo{" + "id=" + id + ", articulo=" + articulo + ", cantidad=" + cantidad + ", ventas=" + ventas + '}';
    }
    
    
    
    
    
    
}
