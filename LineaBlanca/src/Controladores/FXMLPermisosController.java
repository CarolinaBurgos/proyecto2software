/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelo.Peticion;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Toshiba
 */
public class FXMLPermisosController extends FXMLInicioSuperAdminController implements Initializable {

    
    //treeview
    @FXML
    TreeView tvPeticiones;
    @FXML
    Button aprobar, rechazar;
    @FXML
    AnchorPane venta_pane, empleado_pane;
    
    List <Peticion> pets;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        pets = super.buscarPeticionesPendientes();
        //tvPeticiones.setRoot(value);
        
    }    
    
    public List<TreeItem> verPeticiones(){
        
        List <TreeItem> p = new ArrayList<>();
        
        
        
    
    
    
        return p;
    }
    
    
    
    
    
}
