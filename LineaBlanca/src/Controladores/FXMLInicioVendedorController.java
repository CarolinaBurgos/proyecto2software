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
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
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
public class FXMLInicioVendedorController extends ControlLogin implements Initializable {
    
    @FXML
    private TextField TxtNumFactura,TxtCIclient,TxtCantidad;

    @FXML
    private Button BtnLogOut,btnBuscarFactura,BtnBuscar,BtnGenerarVenta;
    
    @FXML
    private ComboBox btnCategorias;
     
    @FXML private TableView tablaProductos;
    @FXML private TableColumn<String, String> tablaMarca;
    @FXML private TableColumn<String,String> tablaPrecio;
    @FXML private TableColumn<String,String> tablaCantidad;
    @FXML private TableColumn<String,String> tablaPrecioTotal;
    @FXML private TableColumn<String,String> tablaDescripcion;
    @FXML private TableColumn<String,String> tablaIdProducto;
    
    @FXML
    private Label TxtValor,TxtValorIva,TxtValorNumerico,TxtValorIvaNumerico,TxtVendedor,TxtNombreVendedor,TxtCedulaVendedor;
 
    private Connection conn;
    
    private ObservableList<ObservableList> data;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        super.connectar();
        conn = this.getConn();
        this.TxtNombreVendedor.setText(FXMLLoginController.user.getNombre());
        this.TxtCedulaVendedor.setText(FXMLLoginController.user.getId_entidad());
        llenarComboBoxArticulo();
        tablaIdProducto.setCellValueFactory(new PropertyValueFactory<>("id"));
        tablaDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        tablaMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        tablaPrecio.setCellValueFactory(new PropertyValueFactory<>("precio_sin_iva"));
        tablaCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        tablaPrecioTotal.setCellValueFactory(new PropertyValueFactory<>("totalArticulo"));
        //precio_sin_iva
    }   
    
    
    @FXML
    public void llenarComboBoxArticulo(){
        //select alm.id_almacen from "LBSASQL"."Empleado" emp join "LBSASQL"."Almacen"	alm on emp.id_almacen=alm.id_almacen where emp.num_cedula='234757689';
        try {
            int numeroAlmacen=0;
            String q1 = "select alm.id_almacen from \"LBSASQL\".\"Empleado\" emp join \"LBSASQL\".\"Almacen\" alm on emp.id_almacen=alm.id_almacen where emp.num_cedula='" + FXMLLoginController.user.getId_entidad()+"'";
            //System.out.println(q1);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(q1);              
            while (rs.next()) {
                numeroAlmacen=Integer.valueOf(rs.getString(1));
               // System.out.println(numeroAlmacen);
            }
            //select art.id_articulo,art.descripcion, art.marca,art.precio_cliente_sin_iva,artAlm.cantidad_articulo_disponible from "LBSASQL"."Articulo" art join "LBSASQL"."Articulo_almacenado" artAlm on art.id_articulo=artAlm.id_articulo join"LBSASQL"."Almacen" alma on artAlm.id_almacen=alma.id_almacen where alma.id_almacen=1; ;	
            String q2 = "select art.id_articulo,art.descripcion, art.marca,art.precio_cliente_sin_iva,artAlm.cantidad_articulo_disponible from \"LBSASQL\".\"Articulo\" art join \"LBSASQL\".\"Articulo_almacenado\" artAlm on art.id_articulo=artAlm.id_articulo join\"LBSASQL\".\"Almacen\" alma on artAlm.id_almacen=alma.id_almacen where alma.id_almacen='"+ numeroAlmacen +"'";
            ResultSet rs2 = stmt.executeQuery(q2); 
            ObservableList<String> items = FXCollections.observableArrayList();
            while (rs2.next()) {
                items.add(rs2.getString(1)+","+rs2.getString(2)+","+rs2.getString(3)+","+rs2.getString(4)+","+rs2.getString(5));
            }
             btnCategorias.setItems(items);

        } catch (SQLException ex) {
            Logger.getLogger(FXMLInicioVendedorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
   @FXML
    public void IngresarArticulo(){
        
    }
    
    
    
    

     
    
   
    public void BuscarCompra(MouseEvent eevent) {
        String query = "SELECT com.id_cliente,art.id_articulo,art.descripcion,art.marca,art.precio_cliente_sin_iva,artven.cantidad_articulo from \"LBSASQL\".\"Compra\" com join \"LBSASQL\".\"Articulos_vendidos\" artven on com.id_compra=artven.id_compra join \"LBSASQL\".\"Articulo\" art on artven.id_articulo=art.id_articulo join \"LBSASQL\".\"Cliente\" cli on com.id_cliente=cli.id_cliente join  \"LBSASQL\".\"Cliente\" clie on clie.id_cliente=com.id_cliente WHERE com.id_compra='"+ this.TxtNumFactura.getText()+"'";
        Statement stmt;
        try {
            ObservableList<ArticuloVenta> row = FXCollections.observableArrayList();
                
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);           
            while (rs.next()) {
                ArticuloVenta art=new ArticuloVenta((int)Integer.valueOf(rs.getString(2)),rs.getString(3),Double.valueOf(rs.getString(5)),rs.getString(4),Integer.valueOf(rs.getShort(6)));
                row.add(art);
                tablaProductos.setItems(row);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(FXMLInicioVendedorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
            
     public void addClientes(MouseEvent event){
        
        try{
                Node n = (Node) event.getSource();
                n.getScene().setRoot(FXMLLoader.load(getClass().getResource("/Views/FXMLRegistrarClientes.fxml")));
               
            }catch(IOException e){
                System.out.println(e);
            }
    }
    
    public void logOut(MouseEvent event){
        try{
                Node n = (Node) event.getSource();
                n.getScene().setRoot(FXMLLoader.load(getClass().getResource("/Views/FXMLLogin.fxml")));
                
            }catch(IOException e){
                System.out.println(e);
            }
    }

    }
