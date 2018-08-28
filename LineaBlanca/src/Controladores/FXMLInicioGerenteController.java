/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelo.Reporte;
import Modelo.ReporteArticulo;
import Modelo.ReporteCliente;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
     
    @FXML private TableView<Reporte> TableArticulos, TableClientes;
    @FXML private TableColumn<Reporte, String> ColIDCl;
    @FXML private TableColumn<Reporte,String> ColNombreCl;
    @FXML private TableColumn<Reporte,String> ColDireccionCl;
    @FXML private TableColumn<Reporte,String> ColTlfnCliente;
    @FXML private TableColumn<Reporte,String> ColMontoCl;
    @FXML private TableColumn<Reporte, String> ColID;
    @FXML private TableColumn<Reporte,String> ColAr;
    @FXML private TableColumn<Reporte,String> ColCant;
    @FXML private TableColumn<Reporte,String> ColVent;
    
    private Connection conn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        super.connectar();
        conn = this.getConn();
        this.startColumns();
        this.startLabels();
    }    
    
    
     public void logOut(MouseEvent event){
         try {
             Node n = (Node) event.getSource();
             n.getScene().setRoot(FXMLLoader.load(getClass().getResource("/Views/FXMLLogin.fxml")));
         } catch (IOException ex) {
             Logger.getLogger(FXMLInicioGerenteController.class.getName()).log(Level.SEVERE, null, ex);
         }            
    }
     
      public void doSearch(MouseEvent event){
        
        try{
                Node n = (Node) event.getSource();
                n.getScene().setRoot(FXMLLoader.load(getClass().getResource("/Views/FXMLBusquedaGenerica.fxml")));
                
                
            }catch(IOException e){
                Logger.getLogger(FXMLInicioGerenteController.class.getName()).log(Level.SEVERE, null, e);
            }
    }
    
      private void startColumns(){
          this.ColID.setCellValueFactory(new PropertyValueFactory<>("id"));
          this.ColAr.setCellValueFactory(new PropertyValueFactory<>("articulo"));
          this.ColCant.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
          this.ColVent.setCellValueFactory(new PropertyValueFactory<>("ventas"));   
          
          this.ColIDCl.setCellValueFactory(new PropertyValueFactory<>("id"));
          this.ColNombreCl.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
          this.ColDireccionCl.setCellValueFactory(new PropertyValueFactory<>("Direccion"));
          this.ColTlfnCliente.setCellValueFactory(new PropertyValueFactory<>("Telefono"));
          this.ColMontoCl.setCellValueFactory(new PropertyValueFactory<>("monot"));
          
         
      }
      
      private void startLabels(){
          this.LabelDate.setText(LocalDate.now().format(DateTimeFormatter.ISO_DATE));
      }
      
      
      public void fillClienteReporte(MouseEvent event){
          if(!this.TxtIDVendedor.getText().equals("")) {
              
              try(PreparedStatement proc = conn.prepareStatement("select * from ReporteVendedor(?)")) {
                    proc.setString(1,this.TxtIDVendedor.getText());
                    try(ResultSet rs = (ResultSet) proc.executeQuery();){
                        while (rs.next()) {
                            this.LblNameVendedor.setText(rs.getString(1));
                            this.LblCantVentas.setText(rs.getString(2));
                            this.LblMonto.setText(rs.getString(3));
                    }                  
            }
              } catch (SQLException ex) {
                  Logger.getLogger(FXMLInicioGerenteController.class.getName()).log(Level.SEVERE, null, ex);
              }
          }   
          
      }
      
      public void fillArticulosReporte(MouseEvent event){
          
         try(PreparedStatement proc = conn.prepareStatement("select * from ReporteArticulo()")) {
             try(ResultSet rs = (ResultSet) proc.executeQuery()){
                 ObservableList<Reporte> items = FXCollections.observableArrayList();
                 while(rs.next()){
                    ReporteArticulo art = new ReporteArticulo((String)rs.getString(1),(String)rs.getString(2),
                    Float.parseFloat(rs.getString(3)),Float.parseFloat(rs.getString(4)));
                    items.add(art);
                    this.TableArticulos.setItems(items);
                }
             }
             
         } catch (SQLException ex) {
             Logger.getLogger(FXMLInicioGerenteController.class.getName()).log(Level.SEVERE, null, ex);
         }
          
      }
      
      public void fillClientesReportes(MouseEvent event){
          try(PreparedStatement proc = conn.prepareStatement("select * from ReporteCliente()")) {
              try(ResultSet rs = (ResultSet) proc.executeQuery()){
                    ObservableList<Reporte> items = FXCollections.observableArrayList();
                    while(rs.next()){
                        ReporteCliente client = new ReporteCliente((String)rs.getString(1),
                         rs.getString(2), rs.getString(3),rs.getString(4),(Float)Float.parseFloat(rs.getString(5)));
                        items.add(client);
                        this.TableClientes.setItems(items);
                    }
              }            
         } catch (SQLException ex) {
             Logger.getLogger(FXMLInicioGerenteController.class.getName()).log(Level.SEVERE, null, ex);
         }
      
      }
      
}
