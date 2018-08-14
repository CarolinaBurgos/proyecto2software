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
public class Cliente extends Ciudadano {
    
    public Cliente(String id_entidad, String nombre, String correo, String telefono, String direccion) {
        super(id_entidad, nombre, correo, telefono, direccion);
    }

    public Cliente(String apellido, String id_entidad, String nombre, String correo, String telefono, String direccion) {
        super(apellido, id_entidad, nombre, correo, telefono, direccion);
    }
    
    
}
