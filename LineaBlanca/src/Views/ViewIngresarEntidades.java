/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

/**
 *
 * @author Carolina
 */
public class ViewIngresarEntidades {
    
    private ViewIngresarArticulos ingresoArticulos;
    private ViewIngresarUsers ingresoUsers;
    private ViewIngresarClientes ingresoClientes;
    private ViewRegistrarTransacciones ingresoTransacciones;

    public ViewIngresarArticulos getIngresoArticulos() {
        return ingresoArticulos;
    }

    public void setIngresoArticulos(ViewIngresarArticulos ingresoArticulos) {
        this.ingresoArticulos = ingresoArticulos;
    }

    public ViewIngresarUsers getIngresoUsers() {
        return ingresoUsers;
    }

    public void setIngresoUsers(ViewIngresarUsers ingresoUsers) {
        this.ingresoUsers = ingresoUsers;
    }

    public ViewIngresarClientes getIngresoClientes() {
        return ingresoClientes;
    }

    public void setIngresoClientes(ViewIngresarClientes ingresoClientes) {
        this.ingresoClientes = ingresoClientes;
    }

    public ViewRegistrarTransacciones getIngresoTransacciones() {
        return ingresoTransacciones;
    }

    public void setIngresoTransacciones(ViewRegistrarTransacciones ingresoTransacciones) {
        this.ingresoTransacciones = ingresoTransacciones;
    }
    
}
