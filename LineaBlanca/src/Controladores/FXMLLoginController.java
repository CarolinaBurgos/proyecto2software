/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import Conexion.*;

/**
 * FXML Controller class
 *
 * @author user
 */
public class FXMLLoginController implements Initializable {
    
    
    
    @FXML
    private TextField lblUser;
    
    @FXML
    private TextField lblPass;
    
    @FXML
    private Button BtnClick;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL  url, ResourceBundle rb) {
        
    }     
    
    public void Conectarse(MouseEvent event) throws IOException {
        //ConexionesDataBase.conect();
        String usr = lblUser.getText();
        String pass = lblUser.getText();
        
        boolean state = false;
        
        if(!usr.isEmpty() && !pass.isEmpty()){
            state = true;
        }
        
        System.out.println(state);
        if(state){
            try{
                Node n = (Node) event.getSource();
                n.getScene().setRoot(FXMLLoader.load(getClass().getResource("/lineablanca/FXMLInicioAdmin.fxml")));
            }catch(Exception e){
                System.out.println(e);
            }
        }else{
            System.out.println("No registrado");
        }           

    }
    
    public String requestUser(Connection conn,String user, String psswd){
        try {
            String sql = " SELECT usuario , contraseña FROM Empleado where user='" + user + "' and contraseña='" + psswd + "'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                return rs.getNString(7);
            }
        } catch (Exception e) {
            System.out.println("ha sucecido un problema");
        }
        return null;
    }
    
    
}
