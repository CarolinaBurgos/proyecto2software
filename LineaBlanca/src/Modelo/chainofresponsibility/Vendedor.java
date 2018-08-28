/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.chainofresponsibility;

import Modelo.Cliente;
import Modelo.Compra;
import Modelo.Empleado;
import java.util.Date;

/**
 *
 * @author Angel Moya
 */
public class Vendedor extends Empleado{

    public Vendedor(String id_entidad, String nombre, String apellido, String userName, String correo, String psswd, String permiso, Date fecha_contratacion, Date fecha_actualizacion, boolean isActivo) {
        super(id_entidad, nombre, apellido, userName, correo, psswd, permiso, fecha_contratacion, fecha_actualizacion, isActivo);
    }
    
    public boolean realizarVenta(Cliente cliente, Compra compra){
        return true;
    }
    
    public boolean AgregarCliente(Cliente cliente){
        return true;
    }
}
