/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelo.*;
import Conexion.*;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
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
public class FXMLInicioVendedorController implements Initializable {

    
    @FXML
    private TextField nombre;
    
    @FXML
    private TableView tablaProductos;
    //String pass = lblUser.getText();
    
    @FXML
    private ComboBox btnCategorias;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    @FXML
    private void inicializar(ActionEvent event) {
        btnCategorias.getItems().removeAll(btnCategorias.getItems());
        btnCategorias.getItems().addAll("codigo", "descripcion", "marca");
       // categorias.getSelectionModel().select("Option B");
    }

    
    
    
    //El
    /*public void BusquedaTodosProductos( Object clase, int tipo) {
        //Vaciar tabla antes de llenarla
        for (int i = 0; i < tablaProductos.getHeight(); i++) {            
        }
        
        //DataBase es mi clase conexion de la base de datos
        ConexionesDataBase haciendoConexion = new ConexionesDataBase();
        Connection instanciaConexion = haciendoConexion.getConn();
        Statement stmt;
            Label label = new Label();
            label.setText("nombre");
        try {
            stmt = instanciaConexion.createStatement();
            Articulo articulo = (Articulo) clase;
            //query para obtener todos los objetos articulos
           // String sq4 = "select art.codigoArticulo,art.descripcion,factExt.noFactura, dist.idDistribuidora,cart.precioUnitario,cart.cantidad from articulo art join compraarticulo cart on art.codigoArticulo=cart.codigoArticulo join facturaExterna factExt on cart.noRegistro=factExt.noRegistro join distribuidora dist on factExt.idDistribuidora=dist.idDistribuidora where descripcion like'" + "%" + articulo.getTxtDescripcion().getText() + "%';";
            //System.out.println(sq4);
            //ResultSet rs4 = stmt.executeQuery(sq4);

            //while (rs4.next()) {
                //Object datosArticulos[] = {rs4.getString(1), rs4.getString(2), rs4.getString(3), rs4.getString(4), rs4.getString(5), rs4.getString(6)};
                //articulo.getModelo().addRow(datosArticulos);
            //}
        } catch (SQLException ex) {
            Logger.getLogger(FXMLInicioVendedorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    
  
}
