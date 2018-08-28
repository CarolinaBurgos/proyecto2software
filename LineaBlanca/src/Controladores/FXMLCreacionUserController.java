/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author user
 */
public class FXMLCreacionUserController extends FXMLLoginController implements Initializable {

    @FXML
    private Button menu;
    @FXML
    private Button BtnRegistration;
    @FXML
    private AnchorPane navList;
    @FXML
    private TextField TxtFName,TxtUser,TxtPwd,TxtDirection,TxtLocalId;
    @FXML
    private TextField TxtLName;
    @FXML
    private TextField TxtMail;
    @FXML
    private TextField TxtCI;
    @FXML
    private CheckBox SelectActive;
    @FXML
    private ComboBox TxtRole;
    @FXML
    private DatePicker selectDate;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        super.initialize(url, rb);
        prepareSlideMenuAnimation();
        sc=new Escenario();
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
    
    public boolean verificarDatos(MouseEvent event){
        if(this.TxtCI==null||this.TxtCI.getText().equals("")||this.TxtFName.getText().equals("")||
                this.TxtLName.getText().equals("")||this.TxtMail.getText().equals("")||
                this.TxtUser.getText().equals("")||
                this.TxtPwd.getText().equals("")||this.TxtLocalId.getText().equals(""))
            return false;
        return true;
    }
    
    public void registrar(MouseEvent event){
        if(this.verificarDatos(event)){
            String query_stmt = this.prepararQuery();
            Statement stmt;
            try {
                stmt = super.getConn().createStatement();
                try(ResultSet rs = stmt.executeQuery(query_stmt)){
                    this.acceptDialogue();
                }                
            } catch (SQLException ex) {
                this.errorDialogue();
                Logger.getLogger(FXMLRegistrarProductosController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
    public String prepararQuery(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        return "INSERT INTO \"LBSASQL\".\"Empleado\"("
                + "num_cedula, nombres, apellidos, usuario, correo, direccion, "
                + "contraseña, rol_actual, fecha_contratacion, fecha_actualizacion,"
                + "empleado activo, id_almacen)	VALUES ('"
                + this.TxtCI.getText()+","+this.TxtFName.getText()+","+
                this.TxtLName.getText()+","+this.TxtMail.getText()+","+
                this.TxtDirection.getText()+","+this.TxtPwd.getText()+","+
                this.TxtRole.getSelectionModel().getSelectedItem().toString()+","+
                this.selectDate.getValue().format(formatter)+","+LocalDate.now().format(formatter)
                +","+true+","+TxtLocalId.getText()+")";

    }
    
}
