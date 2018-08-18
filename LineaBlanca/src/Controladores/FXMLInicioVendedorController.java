/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelo.*;
import Conexion.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import javafx.scene.control.ComboBox;

import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;


/**
 * FXML Controller class
 *
 * @author user
 */
public class FXMLInicioVendedorController extends ControlLogin implements Initializable {
    
    @FXML
    private TextField nombre, TxtCIclient;


    @FXML
    private TableView tablaProductos;
    @FXML
    private Button BtnBuscar, BtnAddClient, BtnGenerarVenta, BtnLogOut;
    @FXML
    private ChoiceBox listLogin;
    
    private Connection conn;
    
    private ObservableList<ObservableList> data;
    
    @FXML
    private Label TxtValornoIva, TxtValorIVA;


    
    @FXML
    private ComboBox btnCategorias;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        super.connectar();
        conn = this.getConn();
    }    

   
    
    //String pass = lblUser.getText();

    
    
    @FXML
    private void inicializar(ActionEvent event) {
        btnCategorias.getItems().removeAll(btnCategorias.getItems());
        btnCategorias.getItems().addAll("codigo", "descripcion", "marca");
       // categorias.getSelectionModel().select("Option B");
    }

    
    private void FillTables(){
        data = FXCollections.observableArrayList();
        String code_Search = this.nombre.getText();
        try {
            Statement state = conn.createStatement();
            String query_llamada_procedure = "";
            ResultSet rs = state.executeQuery(query_llamada_procedure);
            while(rs.next()){
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                //for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                    //Iterate Column
                    row.add(rs.getString("id_articulo"));
                    row.add(rs.getString("categoria"));
                    row.add(rs.getString("marca"));
                    row.add(rs.getString("color"));
                    row.add(rs.getString("consumo_electrico"));
                    row.add(rs.getString("precio_cliente)sin_iva"));
                    row.add(rs.getString("costo_proveedor"));
                //}
                data.add(row);

            }
            this.tablaProductos.setItems(data);
            
        } catch (SQLException ex) {
            Logger.getLogger(FXMLInicioVendedorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
            
    public void addClientes(MouseEvent event){
        
        try{
                Node n = (Node) event.getSource();
                n.getScene().setRoot(FXMLLoader.load(getClass().getResource("/lineablanca/FXMLRegistrarClientes.fxml")));
            }catch(IOException e){
                System.out.println(e);
            }
    }
    
    public void logOut(MouseEvent event){
        try{
                Node n = (Node) event.getSource();
                n.getScene().setRoot(FXMLLoader.load(getClass().getResource("/lineablanca/FXMLLogin.fxml")));
            }catch(IOException e){
                System.out.println(e);
            }
    }

    }

    
  

