/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Conexion.ConexionesDataBase;
import Modelo.CompraCliente;
import Modelo.Empleado;
import Modelo.Peticion;
import Modelo.Venta;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Toshiba
 */
public class FXMLPermisosController extends FXMLInicioSuperAdminController implements Initializable {

    
    //treeview
    @FXML
    Accordion acPeticiones;
    @FXML
    Button aprobar, rechazar;

    
    List <Peticion> pets;
    //Map <Peticion,>
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //inicializando la conexion
        ConexionesDataBase.conect();
        this.conn = super.getConn();
        //sacando las peticiones pendientes
        this.pets = this.buscarPeticionesPendientes();
        //mostrando las peticiones en un cuadro
        
        acPeticiones.getPanes().addAll(verPeticiones(pets));
        
    }    
    
    
    //cargar todas las peticiones al accordion
    public List<TitledPane> verPeticiones(List <Peticion> petcs){
        
        List <TitledPane> p = new ArrayList<>();
        
        
        
        petcs.forEach((Peticion peticion) -> {
            TitledPane tp = new TitledPane();
            tp.setText("Peticion Nº "+ peticion.getId());
            
            GridPane grid = new GridPane();
                    grid.setVgap(4);
                    grid.setPadding(new Insets(5, 5, 5, 5));
                    grid.add(new Label("Empleado que solicitó permisos: "), 0, 0);
                    grid.add(new Label(empleadoPorPeticion(peticion.getId_empleado()).getNombre()), 1, 0);
                    grid.add(new Label("ID de la compra a modificar: "), 0, 1);
                    grid.add(new Label(Integer.toString(peticion.getId_venta())), 1, 1);
                    grid.add(new Label("Razón del cambio: "), 0, 2);
                    grid.add(new Label(peticion.getRazon_cambio()), 1, 2);
                    grid.add(new Label(), 0, 3);
                    grid.add(new Label(), 1, 3);
                    grid.add(aprobar, 0, 4);
                    grid.add(rechazar, 1, 4);
            tp.setContent(grid);
            p.add(tp);
        });
        
        return p;
    }
    
    public Empleado empleadoPorPeticion(String id_empleado){
        
        Empleado ret= new Empleado();
            try {
                String empleado_query = "SELECT num_cedula, nombres, apellidos, usuario " +
                        "	FROM \"LBSASQL\".\"Empleado\" where num_cedula = '"+ id_empleado+"';";
                
                Statement st = this.conn.createStatement();
                ResultSet rs = st.executeQuery(empleado_query);

                
                
                while (rs.next()){
                    
                    String id = rs.getString("num_cedula");
                    String nombre=rs.getString("nombres");
                    String apellido=rs.getString("apellidos");
                    String user=rs.getString("usuario"); 
                    
                    ret.setUserName(user);
                    ret.setNombre(nombre+", "+apellido);
                    ret.setId_entidad("num_cdula");
                    
                }
                
                
                
            } catch (SQLException ex) {
                Logger.getLogger(FXMLPermisosController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
           
        
        return ret;
    
    
    }
    
    
    
    
}
