/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Conexion.ConexionesDataBase;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author user
 */
public class FXMLInicioSuperAdminController extends ConexionesDataBase implements Initializable {

    Connection conn;
    
    @FXML
    private Button BtnLogOut, BtnPermisos, BtnArticulos, BtnVentas;
    @FXML
    private SplitPane SplitUsers, SplitInventario, SplitClientes;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ConexionesDataBase.conect();
         this.conn = super.getConn();
    }    
    
     public void logOut(MouseEvent event){
        try{
                Node n = (Node) event.getSource();
                n.getScene().setRoot(FXMLLoader.load(getClass().getResource("/lineablanca/FXMLLogin.fxml")));
            }catch(IOException e){
                System.out.println(e);
            }
    }
     
    public void crearProducto(MouseEvent event){
        try{
                Node n = (Node) event.getSource();
                n.getScene().setRoot(FXMLLoader.load(getClass().getResource("/lineablanca/FXMLRegistrarProductos.fxml")));
            }catch(IOException e){
                System.out.println(e);
            }
    }
    
    public void goToUsers(MouseEvent event){
        try{
                Node n = (Node) event.getSource();
                n.getScene().setRoot(FXMLLoader.load(getClass().getResource("/lineablanca/FXMLBusquedaGenerica.fxml")));
            }catch(IOException e){
                System.out.println(e);
            }
        
    }
    
}
