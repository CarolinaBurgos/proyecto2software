/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Angel Moya
 */
public class Venta extends Transaccion{
    private Vendedor vendedor;
    private ArrayList<Articulo> detalleCompra;
    private ArrayList<Pago> pago;
    
    public Venta(Date Fecha, Empleado responsable) {
        super(Fecha, responsable);
    }

    public Venta(Vendedor vendedor, ArrayList<Articulo> detalleCompra, ArrayList<Pago> pago, Date Fecha, Empleado responsable) {
        super(Fecha, responsable);
        this.vendedor = vendedor;
        this.detalleCompra = detalleCompra;
        this.pago = pago;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public ArrayList<Articulo> getDetalleCompra() {
        return detalleCompra;
    }

    public void setDetalleCompra(ArrayList<Articulo> detalleCompra) {
        this.detalleCompra = detalleCompra;
    }

    public ArrayList<Pago> getPago() {
        return pago;
    }

    public void setPago(ArrayList<Pago> pago) {
        this.pago = pago;
    }
    
    
    
}
