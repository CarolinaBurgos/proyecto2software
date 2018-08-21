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
public class ArticuloVenta extends Articulo{
    private int cantidad;
    private float totalArticulo;
    
    
    public ArticuloVenta(int id, String descripcion, double precio_sin_iva, String marca,int cantidad) {
        super(id, descripcion, precio_sin_iva, marca);
        this.cantidad=cantidad;
        this.totalArticulo=(float) (precio_sin_iva*cantidad);
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getTotalArticulo() {
        return totalArticulo;
    }

    public void setTotalArticulo(float totalArticulo) {
        this.totalArticulo = totalArticulo;
    }

    @Override
    public String toString() {
        return "ArticuloVenta{" + "cantidad=" + cantidad + ", totalArticulo=" + totalArticulo + '}';
    }

   
    
    
    
}
