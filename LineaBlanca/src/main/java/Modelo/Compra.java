/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;

/**
 *
 * @author Angel Moya
 */
public class Compra {
    private Proveedor proveedor;
    private ArrayList<Articulo> detalleCompra;

    public Compra(Proveedor proveedor, ArrayList<Articulo> detalleCompra) {
        this.proveedor = proveedor;
        this.detalleCompra = detalleCompra;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public ArrayList<Articulo> getDetalleCompra() {
        return detalleCompra;
    }

    public void setDetalleCompra(ArrayList<Articulo> detalleCompra) {
        this.detalleCompra = detalleCompra;
    }
    
    
}
