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
public class Vendedor extends Empleado{
    
    public Vendedor(String id_entidad, String nombre, String correo, String telefono, String direccion) {
        super(id_entidad, nombre, correo, telefono, direccion);
    }

    public Vendedor(String userName, String permiso, String psswd, String apellido, String id_entidad, String nombre, String correo, String telefono, String direccion) {
        super(userName, permiso, psswd, apellido, id_entidad, nombre, correo, telefono, direccion);
    }
    
    public boolean realizarVenta(Cliente cliente, Compra compra){
        return true;
    }
    
    public boolean AgregarCliente(Cliente cliente){
        return true;
    }
}
