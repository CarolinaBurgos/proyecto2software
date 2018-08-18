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
import Modelo.Empleado;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author user
 */
public class FXMLLoginController extends ControlLogin implements Initializable {
    
    
    
    @FXML
    private TextField lblUser;
    
    @FXML
    private TextField lblPass;
    
    @FXML
    private Button BtnClick;
    
    private Empleado emp;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL  url, ResourceBundle rb) {
        super.connectar();
        
    }     
    
    public void Conectarse(MouseEvent event) throws IOException {
        //ConexionesDataBase.conect();
        String usr = lblUser.getText();
        String pass = lblPass.getText();
        
        String requestedUser = this.requestUser(super.getConn(),usr,pass);
        
        boolean state = false;
        
        if(!usr.isEmpty() && !pass.isEmpty()){
            state = true;
        }
        
        System.out.println(state);
        if(state){
            try{
                Node n = (Node) event.getSource();
                n.getScene().setRoot(FXMLLoader.load(getClass().getResource("/lineablanca/FXMLInicio"+requestedUser+".fxml")));
            }catch(Exception e){
                System.out.println(e);
            }
        }else{
            System.out.println("No registrado");
        }           

    }
    
    public String requestUser(Connection conn,String user, String psswd){
        try {
            String sql = " SELECT * FROM \"LBSASQL\".\"Empleado\" where usuario='" + user + "' and contraseña='" + psswd + "'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
               // System.out.println(rs.getNString(1)+ rs.getNString(2)+ rs.getNString(3)+ rs.getNString(4)+ rs.getNString(5)+rs.getNString(7)+ rs.getNString(8)+ Date.valueOf(rs.getNString(9)));
                this.emp = this.setEmpleado(rs);
                return rs.getNString(8); //cargo
            }else{
                JOptionPane.showMessageDialog(null, "Usuario no registrado", "Error de ingreso", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println("ha sucecido un problema");
        }
        return null;
    }
    
    public Empleado setEmpleado(ResultSet rs){
        Empleado user = null;
        try {
           /* user = new Empleado(rs.getNString(1),rs.getNString(2),
                    rs.getNString(3),rs.getNString(4),rs.getNString(5),rs.getNString(6),rs.getNString(7)
                    ,rs.getNString(8),rs.getNString(9));*/
        user = new Empleado(rs.getNString(1), rs.getNString(2), rs.getNString(3), rs.getNString(4), rs.getNString(5), rs.getNString(7), rs.getNString(8), Date.valueOf(rs.getNString(9)), Date.valueOf(rs.getNString(10)), Boolean.getBoolean(rs.getString(11)));
            user.toString();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
    
    public Empleado getEmpleado(){
        return this.emp;
    }
    
}
