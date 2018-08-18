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
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author user
 */
public class FXMLRegistrarProductosController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TextArea AreaDesc;
    @FXML
    private Button BtnDespliegue, BtnHome, BtnRegistro;
    @FXML
    private ColorPicker Chcolr; 
    @FXML
    private Pane PaneSlide;
    @FXML
    private TextField TxtCat,TxtElectrico,TxtId,TxtMar,TxtPrecioProv;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
