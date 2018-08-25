/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author user
 */
public class FXMLRegistrarClientesController extends ControlVendedor implements Initializable {

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
        super.connectar();
    }    
    
    @FXML
    private void volverAlMenuAnterior(MouseEvent e){
        try{
            Node n = (Node) e.getSource();
            n.getScene().setRoot(FXMLLoader.load(getClass().getResource("/Views/FXMLInicioVendedor.fxml")));
        }catch(Exception ex){
            failure(ex.toString());
        }
    
    }
    
    
    @FXML
    public void agregarCliente(MouseEvent e){
    
        String nombre=TxtNombre.getText();
        String mail=TxtMail.getText();
        String direccion=TxtDireccion.getText();
        String numero_identidad=TxtId.getText();
        
        String id_cliente;
        
        
        //fecha local 
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneOffset.systemDefault());
        Instant instant = zonedDateTime.toInstant();
        Date date = Date.from(instant);
        java.sql.Date sDate = convertUtilToSql(date);

     //query para agregar cliente
        String query;
        
        
        
        query= "INSERT INTO \"LBSASQL\".\"Cliente\"(\n" + 
                "nombre, correo, direccion, fecha_creacion, fecha_ultima_actualizacion, reg_eliminado)\n" +
                "VALUES (?,? ,?,?, ?, ?);";
        
        try {
            //INSERTAR EN CLIENTE
            PreparedStatement st= super.conexion.prepareStatement(query);
            st.setString(1, nombre);
            st.setString(2, mail);
            st.setString(3, direccion);
            st.setDate(4, sDate);
            st.setDate(5, sDate);
            st.setBoolean(6, false);
            st.executeUpdate();
            
            System.out.println("Cliente registrado con éxito");
        
            Statement smnt= super.conexion.createStatement();
            ResultSet rs = smnt.executeQuery("SELECT id_cliente FROM \"LBSASQL\".\"Cliente\" ORDER BY id_cliente DESC LIMIT 1;");
            int key;
            
            if (rs != null && rs.next()) {
                System.out.println("key");
                key = rs.getInt(1);
                if (numero_identidad.length()==13)  insertarClienteContribuyenteReg(numero_identidad, key, nombre, false);
                else if (numero_identidad.length()==10) insertarClienteCiudadano(numero_identidad,key);
                else failure("Numero invalido de caracteres");
                

            }
            

        } 
        catch (SQLException ex) {
            Logger.getLogger(FXMLRegistrarClientesController.class.getName()).log(Level.SEVERE, null, ex);
            failure(ex.toString());
        }
        
        
        
    
    }
    
    private void insertarClienteCiudadano(String num_cedula, int id_cliente){
    
        try {
            PreparedStatement st = super.conexion.prepareStatement("INSERT INTO \"LBSASQL\"."
                    + "\"Cliente_ciudadano\" (num_cedula, id_cliente, reg_eliminado) VALUES (?, ?, ?);");
            st.setString(1, num_cedula);
            st.setInt(2, id_cliente);
            st.setBoolean(3, false);
            st.executeUpdate();
            st.close();
            System.out.println("Cliente ciudadano agregado");
        } catch (SQLException ex) {
            Logger.getLogger(FXMLRegistrarClientesController.class.getName()).log(Level.SEVERE, null, ex);
            failure(ex.toString());
        }
       
    
    }
    
    
        private void insertarClienteContribuyenteReg(String ruc, int id_cliente, String rs, boolean ce){
    
        try {
            
            String query = "INSERT INTO \"LBSASQL\".\"Cliente_contribuyente_registrado\"(\"RUC\", id_cliente, razon_social, es_contrib_especial, reg_eliminado)"+
	"VALUES (?, ?, ?, ?, ?);";
            PreparedStatement st = super.conexion.prepareStatement(query);
            st.setString(1, ruc);
            st.setInt(2, id_cliente);
            st.setString(3, rs);
            st.setBoolean(4, ce);
            st.setBoolean(5, false);
            st.executeUpdate();
            st.close();
            System.out.println("Cliente contribuyente registrado agregado");
        } catch (SQLException ex) {
            Logger.getLogger(FXMLRegistrarClientesController.class.getName()).log(Level.SEVERE, null, ex);
            failure(ex.toString());
        }
       
    
    }
    
    
    
    
    private void failure(String error){
            Alert failure;
            failure = new Alert (Alert.AlertType.ERROR);
            failure.setTitle("Error al realizar la acción.");
            failure.setContentText("Problema: \n>>"+error);
            failure.showAndWait();
    
    }
    private void success(String data){
            Alert success;
            success = new Alert (Alert.AlertType.INFORMATION);
            success.setTitle("Accion realizada con éxito.");
            success.setContentText("Cliente agregado: \n>>"+data);
            success.showAndWait();
    
    }
    

    private java.sql.Date convertUtilToSql(Date date) {
        java.sql.Date sDate = new java.sql.Date(date.getTime());
        return sDate;
    }
    
    
}
