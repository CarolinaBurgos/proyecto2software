/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author user
 */
public class FXMLCreacionUserController implements Initializable {

    @FXML
    private Button menu;
    @FXML
    private Button BtnRegistration;
    @FXML
    private AnchorPane navList;
    @FXML
    private TextField TxtFName;
    @FXML
    private TextField TxtLName;
    @FXML
    private TextField TxtMail;
    @FXML
    private TextField TxtCI;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        navList.setItems(FXCollections.observableArrayList("Red","Yellow","Blue"));
        prepareSlideMenuAnimation();
    }    

    private void prepareSlideMenuAnimation() {
        TranslateTransition openNav=new TranslateTransition(new Duration(350), navList);
        openNav.setToX(0);
        TranslateTransition closeNav=new TranslateTransition(new Duration(350), navList);
        menu.setOnAction((ActionEvent evt)->{
            if(navList.getTranslateX()!=0){
                openNav.play();
            }else{
                closeNav.setToX(-(navList.getWidth()));
                closeNav.play();
            }
        });
    }   
    
    public void detectCI(){
    
            TxtCI.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")&&TxtCI.getText().length()<10) {
                    TxtCI.setText(oldValue);
                }
            });
        
    }
    
}
