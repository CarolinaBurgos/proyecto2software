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
public class ContribuyenteRegistrado extends Entidad implements Cliente{
    
    protected boolean esContibEspecial;

    public ContribuyenteRegistrado(String id_entidad, String nombre, String correo, String telefono, String direccion, boolean ce) {
        super(id_entidad, nombre, correo, telefono, direccion);
        this.esContibEspecial=ce;
    }


    public boolean isEsContibEspecial() {
        return esContibEspecial;
    }

    public void setEsContibEspecial(boolean esContibEspecial) {
        this.esContibEspecial = esContibEspecial;
    }

    @Override
    public boolean validarID() {
        if (this.id_entidad.length()==13) return true;
        else return false;
    }

    @Override
    public String toString() {
        return super.toString()+", '"+esContibEspecial+"'";
    }
    
    
    
    
}
