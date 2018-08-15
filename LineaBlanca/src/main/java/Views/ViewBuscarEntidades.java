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
public class ViewBuscarEntidades {
    
    private ViewCotizarArticulo cotizarArticulo;
    private ViewVerCliente verCliente;
    private ViewBuscarTransacciones buscarTransaccione;
    
    public void update(){
         //INSERTAR CODIGO
    }
    
    public void showDataTotal(){
         //INSERTAR CODIGO
    }
    
    public void mostrarDatosFiltro(){
         //INSERTAR CODIGO
    }

    public ViewCotizarArticulo getCotizarArticulo() {
        return cotizarArticulo;
    }

    public void setCotizarArticulo(ViewCotizarArticulo cotizarArticulo) {
        this.cotizarArticulo = cotizarArticulo;
    }

    public ViewVerCliente getVerCliente() {
        return verCliente;
    }

    public void setVerCliente(ViewVerCliente verCliente) {
        this.verCliente = verCliente;
    }

    public ViewBuscarTransacciones getBuscarTransaccione() {
        return buscarTransaccione;
    }

    public void setBuscarTransaccione(ViewBuscarTransacciones buscarTransaccione) {
        this.buscarTransaccione = buscarTransaccione;
    }
    
}
