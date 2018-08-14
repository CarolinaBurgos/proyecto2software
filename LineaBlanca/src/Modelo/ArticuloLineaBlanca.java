/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.awt.Color;

/**
 *
 * @author Angel Moya
 */
public class ArticuloLineaBlanca extends Articulo{
    private Color color;
    private int consumoElectrico;
    
    public ArticuloLineaBlanca(int id, String descripcion, double precio_sin_iva, double costo_proveedor, String marca) {
        super(id, descripcion, precio_sin_iva, costo_proveedor, marca);
    }

    public ArticuloLineaBlanca(Color color, int consumoElectrico, int id, String descripcion, double precio_sin_iva, double costo_proveedor, String marca) {
        super(id, descripcion, precio_sin_iva, costo_proveedor, marca);
        this.color = color;
        this.consumoElectrico = consumoElectrico;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getConsumoElectrico() {
        return consumoElectrico;
    }

    public void setConsumoElectrico(int consumoElectrico) {
        this.consumoElectrico = consumoElectrico;
    }
    
    
}
