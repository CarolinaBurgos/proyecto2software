/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Carolina
 */
public class Telefono {
    
    private String telefono;
    private String codigoArea;

    public String getTelefono() {
        return codigoArea + telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCodigoArea(String codigoArea) {
        this.codigoArea = codigoArea;
    }
    
}
