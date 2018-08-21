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
public class Articulo {
    private int id;
    private String descripcion;
    private double precio_sin_iva;
    private double costo_proveedor;
    private String marca;    

    public Articulo(int id, String descripcion, double precio_sin_iva, double costo_proveedor, String marca) {
        this.id = id;
        this.descripcion = descripcion;
        this.precio_sin_iva = precio_sin_iva;
        this.costo_proveedor = costo_proveedor;
        this.marca = marca;
    }

    public Articulo(int id, String descripcion, double precio_sin_iva, String marca) {
        this.id = id;
        this.descripcion = descripcion;
        this.precio_sin_iva = precio_sin_iva;
        this.marca = marca;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio_sin_iva() {
        return precio_sin_iva;
    }

    public void setPrecio_sin_iva(double precio_sin_iva) {
        this.precio_sin_iva = precio_sin_iva;
    }

    public double getCosto_proveedor() {
        return costo_proveedor;
    }

    public void setCosto_proveedor(double costo_proveedor) {
        this.costo_proveedor = costo_proveedor;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return "Articulo{" + "id=" + id + ", descripcion=" + descripcion + ", precio_sin_iva=" + precio_sin_iva + ", costo_proveedor=" + costo_proveedor + ", marca=" + marca + '}';
    }
    
    
}
