/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Conexion.ConexionesDataBase;
import Constantes.Constantes;
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
public class FXMLInicioAdminController extends ConexionesDataBase implements Initializable {

    Escenario sc;
    Connection conn;
    
    @FXML
    private Button BtnLogOut, BtnArticulos, BtnVentas;
    @FXML
    private SplitPane SplitUsers, SplitInventario, SplitClientes;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ConexionesDataBase.conect();
         this.conn = super.getConn();
         sc=new Escenario();
    }    
    
     public void logOut(MouseEvent event){
         
        sc.cambioEscenaActual(event, Constantes.LOGIN_HEIGHT, Constantes.LOGIN_WIDTH, "/Views/FXMLLogin.fxml"); 
    }
     
    
    public void crearProducto(MouseEvent event){
        
        sc.cambioEscenaActual(event, Constantes.REGP_HEIGHT, Constantes.REGP_WIDTH, "/Views/FXMLRegistrarProductos.fxml");
    }
    
    public void goToUsers(MouseEvent event){
        
        sc.cambioEscenaActual(event, Constantes.LOGIN_WIDTH, Constantes.REGP_WIDTH, "/Views/FXMLBusquedaGenerica.fxml");
        
    }
}
