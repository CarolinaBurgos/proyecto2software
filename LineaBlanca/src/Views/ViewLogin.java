/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Controladores.*;

/**
 *
 * @author Carolina
 */
public class ViewLogin {
    
    private ControlLogin ctrLogin;
    private ViewInicioAdministrador inicioAdmin;
    private ViewInicioGerente inicioGerente;
    private ViewInicioVendedor inicioVendedor;
    
    public void render(){
         //INSERTAR CODIGO
    }

    public ControlLogin getCtrLogin() {
        return ctrLogin;
    }

    public void setCtrLogin(ControlLogin ctrLogin) {
        this.ctrLogin = ctrLogin;
    }

    public ViewInicioAdministrador getInicioAdmin() {
        return inicioAdmin;
    }

    public void setInicioAdmin(ViewInicioAdministrador inicioAdmin) {
        this.inicioAdmin = inicioAdmin;
    }

    public ViewInicioGerente getInicioGerente() {
        return inicioGerente;
    }

    public void setInicioGerente(ViewInicioGerente inicioGerente) {
        this.inicioGerente = inicioGerente;
    }

    public ViewInicioVendedor getInicioVendedor() {
        return inicioVendedor;
    }

    public void setInicioVendedor(ViewInicioVendedor inicioVendedor) {
        this.inicioVendedor = inicioVendedor;
    }
    
    
    
}
