/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelo.*;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;


/**
 * FXML Controller class
 *
 * @author user
 */
public class FXMLInicioVendedorController extends ControlLogin implements Initializable {
    private double total,totalConIva;
    @FXML
    private TextField TxtNumFactura,TxtCIclient,TxtCantidad;

    @FXML
    private Button BtnLogOut,btnBuscarFactura,BtnIngresarArticulo,BtnGenerarVenta,BtnIngresar;
    
    private ObservableList<ArticuloVenta> row = FXCollections.observableArrayList();

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
        obtenerNumeroFactura();
        this.TxtNombreVendedor.setText(FXMLLoginController.user.getNombre()+" "+FXMLLoginController.user.getApellido());
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
        try(Statement stmt = conn.createStatement()) {
            int numeroAlmacen=0;
            String q1 = "select alm.id_almacen from \"LBSASQL\".\"Empleado\" emp join \"LBSASQL\".\"Almacen\" alm on emp.id_almacen=alm.id_almacen where emp.num_cedula='" + FXMLLoginController.user.getId_entidad()+"'";
            try(ResultSet rs = stmt.executeQuery(q1)){
                while (rs.next()) {
                    numeroAlmacen=Integer.valueOf(rs.getString(1));
                }
            }
                          
            String q2 = "select art.id_articulo,art.descripcion, art.marca,art.precio_cliente_sin_iva,artAlm.cantidad_articulo_disponible from \"LBSASQL\".\"Articulo\" art join \"LBSASQL\".\"Articulo_almacenado\" artAlm on art.id_articulo=artAlm.id_articulo join\"LBSASQL\".\"Almacen\" alma on artAlm.id_almacen=alma.id_almacen where alma.id_almacen='"+ numeroAlmacen +"'";
            try(ResultSet rs2 = stmt.executeQuery(q2)){
                ObservableList<String> items = FXCollections.observableArrayList();
                while (rs2.next()) {
                    items.add(rs2.getString(1)+","+rs2.getString(2)+","+rs2.getString(3)+","+rs2.getString(4)+","+rs2.getString(5));
                }
                btnCategorias.setItems(items);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLInicioVendedorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    public void IngresarArticulo(MouseEvent eevent){
        int cantidad= Integer.valueOf(TxtCantidad.getText());
        String selection = (String) this.btnCategorias.getSelectionModel().getSelectedItem();
        String[] seleccionArr=selection.split(",");
        if(cantidad <=(int)Integer.valueOf(seleccionArr[4])){
            ArticuloVenta art=new ArticuloVenta((int)Integer.valueOf(seleccionArr[0]),seleccionArr[1],Double.valueOf(seleccionArr[3]),seleccionArr[2],cantidad);
            row.add(art);
            tablaProductos.setItems(row);
            total=total+art.getTotalArticulo();
            TxtValorNumerico.setText(String.valueOf(total));
            totalConIva=total+(total*0.12);
            TxtValorIvaNumerico.setText(String.valueOf(totalConIva));
            
        }else{
            JOptionPane.showMessageDialog(null, "La cantidad ingresada es incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
        }  
    }
    
    public void generarVenta(MouseEvent eevent){
        try {
            if(TxtCIclient.getText().isEmpty()){
                    MensajeError("Ingrese a un cliente");
            }
            if(tablaProductos.getItems().isEmpty()){
                    MensajeError("Ingrese articulos");
            }
            ObservableList<ArticuloVenta> articuloVenta =  tablaProductos.getItems();

            String sq = "INSERT INTO \"LBSASQL\".\"Compra\" (tipo_comprobante_venta, fecha_compra, monto, id_cliente, id_empleado, reg_eliminado) VALUES(?,?,?,?,?,?)";
            try(PreparedStatement stmt = conn.prepareStatement(sq)){
                stmt.setString(1,"fac");
                stmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
                stmt.setDouble(3, Double.valueOf(TxtValorIvaNumerico.getText()));
                stmt.setInt(4, Integer.valueOf(TxtCIclient.getText()));
                stmt.setString(5, FXMLLoginController.user.getId_entidad());
                stmt.setBoolean(6, false);
                stmt.executeUpdate();
            }                               
            for(int i=0;i<articuloVenta.size();i++){
                String sq1 = "INSERT INTO \"LBSASQL\".\"Articulos_vendidos\" (id_compra, id_articulo, cantidad_articulo) VALUES(?,?,?)";
                try(PreparedStatement stmt1 = conn.prepareStatement(sq1)){
                    stmt1.setInt(1,Integer.valueOf(TxtNumFactura.getText()));
                    stmt1.setInt(2,articuloVenta.get(i).getId());
                    stmt1.setInt(3, articuloVenta.get(i).getCantidad());
                    stmt1.executeUpdate();
                }
                 
            }
            JOptionPane.showMessageDialog(null, "Compra ingresada con exito", "Exito", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException | RuntimeException ex) {
            Logger.getLogger(FXMLInicioVendedorController.class.getName()).log(Level.SEVERE, null, ex);
            }   

    }

    public void MensajeError(String mensaje){
            JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    @FXML
    public void obtenerNumeroFactura(){
        
        try(Statement stmt = conn.createStatement()) {
            String query = "SELECT id_compra FROM \"LBSASQL\".\"Compra\" order BY id_compra DESC LIMIT 1;";
            try(ResultSet rs = stmt.executeQuery(query)){
                while (rs.next()) {
                    int numfac=Integer.valueOf(rs.getString(1))+1;
                    TxtNumFactura.setText(String.valueOf(numfac));
                }
            }             
        } catch (SQLException ex) {
            Logger.getLogger(FXMLInicioVendedorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

     
    
   
    public void BuscarCompra(MouseEvent eevent) {
        String query = "SELECT com.id_cliente,art.id_articulo,art.descripcion,art.marca,art.precio_cliente_sin_iva,artven.cantidad_articulo from \"LBSASQL\".\"Compra\" com join \"LBSASQL\".\"Articulos_vendidos\" artven on com.id_compra=artven.id_compra join \"LBSASQL\".\"Articulo\" art on artven.id_articulo=art.id_articulo join \"LBSASQL\".\"Cliente\" cli on com.id_cliente=cli.id_cliente join  \"LBSASQL\".\"Cliente\" clie on clie.id_cliente=com.id_cliente WHERE com.id_compra="+ this.TxtNumFactura.getText()+"";
        try(Statement stmt = conn.createStatement()) {
            ObservableList<ArticuloVenta> row = FXCollections.observableArrayList();
            try(ResultSet rs = stmt.executeQuery(query)){
                while (rs.next()) {
                    TxtCIclient.setText(rs.getString(1));
                    ArticuloVenta art=new ArticuloVenta((int)Integer.valueOf(rs.getString(2)),rs.getString(3),Double.valueOf(rs.getString(5)),rs.getString(4),Integer.valueOf(rs.getShort(6)));
                    row.add(art);
                    tablaProductos.setItems(row);
                }
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
                Logger.getLogger(FXMLInicioVendedorController.class.getName()).log(Level.SEVERE, null, e);
            }
    }
    
    public void logOut(MouseEvent event){
        try{
                Node n = (Node) event.getSource();
                n.getScene().setRoot(FXMLLoader.load(getClass().getResource("/Views/FXMLLogin.fxml")));
                
            }catch(IOException e){
                Logger.getLogger(FXMLInicioVendedorController.class.getName()).log(Level.SEVERE, null, e);
            }
    }

    }
