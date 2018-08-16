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
public class Locaciones {
    private String ubicacion;
    private String codigo;
    private ArrayList<Articulo> inventario;
    private ArrayList<Empleado> personal;

    public Locaciones(String ubicacion, String codigo, ArrayList<Articulo> inventario, ArrayList<Empleado> personal) {
        this.ubicacion = ubicacion;
        this.codigo = codigo;
        this.inventario = inventario;
        this.personal = personal;
    }

    public void pedirAbastecimiento(){
        //INSERT CODE
    }
    
    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public ArrayList<Articulo> getInventario() {
        return inventario;
    }

    public void setInventario(ArrayList<Articulo> inventario) {
        this.inventario = inventario;
    }

    public ArrayList<Empleado> getPersonal() {
        return personal;
    }

    public void setPersonal(ArrayList<Empleado> personal) {
        this.personal = personal;
    }
    
    
}
