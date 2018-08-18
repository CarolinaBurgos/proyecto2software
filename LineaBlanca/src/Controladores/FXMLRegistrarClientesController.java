/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author user
 */
public class FXMLRegistrarClientesController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button BtnDespliegue;
    @FXML
    private TextField TxtNombre,TxtMail,TxtDireccion,TxtId;
    @FXML
    private Button BtnRegistro;
    @FXML
    private Pane PaneSlide;
    @FXML
    private Button BtnHome;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
