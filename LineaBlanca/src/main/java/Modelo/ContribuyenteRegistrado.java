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
public class ContribuyenteRegistrado {
    protected boolean esContibEspecial;

    public ContribuyenteRegistrado(boolean esContibEspecial) {
        this.esContibEspecial = esContibEspecial;
    }

    public boolean isEsContibEspecial() {
        return esContibEspecial;
    }

    public void setEsContibEspecial(boolean esContibEspecial) {
        this.esContibEspecial = esContibEspecial;
    }
    
    
}
