/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author user
 */
public class FXMLInicioGerenteController extends ControlLogin implements Initializable {

     @FXML
    private TextField TxtIDVendedor;
     
     @FXML
    private Label LabelDate, LblNameVendedor,LblCantVentas, LblMonto;

    @FXML
    private Button BtnLogOut,btnRptVendedor,BtnReporteArticulos,BtnReporteClientes,
            BtnConsultas, BtnReportes, BtnVentas;
    
    @FXML
    private ComboBox btnCategorias;
     
    @FXML private TableView TableArticulos, TableClientes;
    @FXML private TableColumn<String, String> ColIDCI;
    @FXML private TableColumn<String,String> ColNombreCl;
    @FXML private TableColumn<String,String> ColDireccionCl;
    @FXML private TableColumn<String,String> ColTlfnCliente;
    @FXML private TableColumn<String,String> ColMontoCl;
    @FXML private TableColumn<String, String> ColID;
    @FXML private TableColumn<String,String> ColAr;
    @FXML private TableColumn<String,String> ColCant;
    @FXML private TableColumn<String,String> ColVent;
    
    private Connection conn;
    
    private ObservableList<ObservableList> data;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        super.connectar();
        conn = this.getConn();
        //this.startColumns();
        //this.startLabels();
    }    
    
    
     public void logOut(MouseEvent event){
        try{
                Node n = (Node) event.getSource();
                n.getScene().setRoot(FXMLLoader.load(getClass().getResource("/Views/FXMLLogin.fxml")));
                
            }catch(IOException e){
                System.out.println(e);
            }
    }
     
      public void doSearch(MouseEvent event){
        
        try{
                Node n = (Node) event.getSource();
                n.getScene().setRoot(FXMLLoader.load(getClass().getResource("/Views/FXMLBusquedaGenerica.fxml")));
                
                
            }catch(IOException e){
                System.out.println(e);
            }
    }
    
      public void startColumns(){
          this.ColAr.setCellValueFactory(new PropertyValueFactory<>("Articulos"));
          this.ColID.setCellValueFactory(new PropertyValueFactory<>("ID"));
          this.ColVent.setCellValueFactory(new PropertyValueFactory<>("Ventas"));
          this.ColCant.setCellValueFactory(new PropertyValueFactory<>("Cantidad"));
          
          this.ColDireccionCl.setCellValueFactory(new PropertyValueFactory<>("Direccion"));
          this.ColIDCI.setCellValueFactory(new PropertyValueFactory<>("ID"));
          this.ColMontoCl.setCellValueFactory(new PropertyValueFactory<>("Monto Promedio"));
          this.ColNombreCl.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
          this.ColTlfnCliente.setCellValueFactory(new PropertyValueFactory<>("Telefono"));
      }
      
      public void startLabels(){
          this.LabelDate.setText(LocalDate.now().format(DateTimeFormatter.ISO_DATE));
      }
      
      
      public void fillClienteReporte(MouseEvent event){
          if(!this.TxtIDVendedor.getText().equals("")) {
              
              try {
                  PreparedStatement proc = conn.prepareStatement("select * from public.ReporteVendedor(?)");
                    proc.setString(1,this.TxtIDVendedor.getText());
                    ResultSet rs = (ResultSet) proc.executeQuery();
                  /*String query_resultSet = "SELECT * FROM ReporteVendedor('"+this.TxtIDVendedor.getText()+"')";
                  Statement stmt = conn.createStatement(); 
                  ResultSet rs = stmt.executeQuery(query_resultSet);*/
                  while (rs.next()) {
                      System.out.println("Entra aqui");
                    this.LblNameVendedor.setText(rs.getString(1));
                    this.LblCantVentas.setText(rs.getString(2));
                    this.LblMonto.setText(rs.getString(3));
            }
              } catch (SQLException ex) {
                  Logger.getLogger(FXMLInicioGerenteController.class.getName()).log(Level.SEVERE, null, ex);
              }
          }   
          
      }
      
      public void fillArticulosReporte(MouseEvent event){
          
         try {
             PreparedStatement proc = conn.prepareStatement("select * from public.ReporteArticulo()");
            ResultSet rs = (ResultSet) proc.executeQuery();
             /*String query = "SELECT * FROM ReporteArticulo()";
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query);*/
             ObservableList<String> items = FXCollections.observableArrayList();
             while(rs.next()){
                 ArrayList<String> results = new ArrayList<>(Arrays.asList(rs.getString(1),
                         rs.getString(2), rs.getString(3),rs.getString(4)));
                 items.addAll(results);
                 this.TableArticulos.setItems(items);
             }
         } catch (SQLException ex) {
             Logger.getLogger(FXMLInicioGerenteController.class.getName()).log(Level.SEVERE, null, ex);
         }
          
      }
      
      public void fillClientesReportes(MouseEvent event){
          try {
              PreparedStatement proc = conn.prepareStatement("select * from public.ReporteCliente");
            ResultSet rs = (ResultSet) proc.executeQuery();
             /*String query = "SELECT * FROM ReporteCliente()";
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query);*/
             ObservableList<String> items = FXCollections.observableArrayList();
             while(rs.next()){
                 System.out.println("Entra aqui");
                 ArrayList<String> results = new ArrayList<>(Arrays.asList(rs.getString(1),
                         rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5)));
                 System.out.println(results);
                 items.addAll(results);
                 this.TableClientes.setItems(items);
             }
         } catch (SQLException ex) {
             Logger.getLogger(FXMLInicioGerenteController.class.getName()).log(Level.SEVERE, null, ex);
         }
      
      }
      
}
