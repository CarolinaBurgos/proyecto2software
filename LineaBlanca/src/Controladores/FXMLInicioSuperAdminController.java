/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Conexion.ConexionesDataBase;
import Modelo.Peticion;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import com.jfoenix.controls.JFXSnackbar;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author user
 */
public class FXMLInicioSuperAdminController extends ConexionesDataBase implements Initializable {

    Connection conn;
   
    
    @FXML
    
    AnchorPane rootPane;
    
    @FXML 
    
    HBox hbBottom;
    
    @FXML
    private Button BtnLogOut, BtnPermisos, BtnArticulos, BtnVentas;
    @FXML
    private SplitPane SplitUsers, SplitInventario, SplitClientes;
    
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         ConexionesDataBase.conect();
         this.conn = super.getConn();
         //inicializando las peticiones pendientes
          List <Peticion> peticiones= buscarPeticionesPendientes();
         int numpet = peticiones.size();
         
         
         
         //configurando notificaciones
         JFXSnackbar sb= new JFXSnackbar(rootPane);
         sb.setLayoutX(530);
         sb.setLayoutY(410);
        if (numpet>0) sb.show("Bienvenido, tiene "+
                 numpet
                 +" peticion(es) de permiso pendiente(s)", 
                 "Abrir peticiones pendientes", 5000,
                 abrirPeticiones(sb));
    }    
    
    
    
    
    
            @FXML
     public void logOut(MouseEvent event){
        try{
                Node n = (Node) event.getSource();
                n.getScene().setRoot(FXMLLoader.load(getClass().getResource("/Views/FXMLLogin.fxml")));
            }catch(IOException e){
                System.out.println(e);
            }
    }   
          @FXML   
    public void crearProducto(MouseEvent event){
        try{
            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/FXMLRegistrarProductos.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Registrar producto nuevo");
            stage.setScene(new Scene(root1)); 
            stage.show();
                      
            
        } catch (IOException ex) {
            Logger.getLogger(FXMLInicioSuperAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
         @FXML   
    public void goToUsers(MouseEvent event){
                try{
            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/FXMLBusquedaGenerica.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            
            Stage stage = new Stage();
            stage.setTitle("Búsqueda");
            stage.setScene(new Scene(root1)); 
            stage.show();
                      
            
        } catch (IOException ex) {
            Logger.getLogger(FXMLInicioSuperAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
        @FXML
    public void inventario(MouseEvent event){
        
        try{
            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/FXMLBusquedaGenerica.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            
            Stage stage = new Stage();
            stage.setTitle("Búsqueda");
            stage.setScene(new Scene(root1)); 
            stage.show();
                      
            
        } catch (IOException ex) {
            Logger.getLogger(FXMLInicioSuperAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    
    public List<Peticion> buscarPeticionesPendientes(){
        
        List <Peticion> p=new ArrayList<>();
        try {
            
            
            String query = "SELECT id_peticion, id_empleado, id_venta, aprobacion_pendiente, peticion_aceptada\n" +
                    "	FROM \"LBSASQL\".\"Peticion_modif_venta\"   WHERE aprobacion_pendiente = 'true';";
            
            Statement smnt= conn.createStatement();
            ResultSet rs = smnt.executeQuery(query);
            
            
            while (rs.next()){
            
                p.add(new Peticion(rs.getInt("id_peticion"),
                                            rs.getString("id_empleado"),
                                            rs.getInt("id_venta"),
                                            rs.getBoolean("aprobacion_pendiente"),
                                            rs.getBoolean("peticion_aceptada")
                ));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(FXMLInicioSuperAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            return p;
        
    }
    
    private EventHandler abrirPeticiones(JFXSnackbar bar){
        
        EventHandler handler=new EventHandler() {
            
            
            @Override
            public void handle(Event event) {
                try{
                    bar.unregisterSnackbarContainer(rootPane);
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/FXMLPermisos.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setTitle("Peticiones pendientes");
                    stage.setScene(new Scene(root1)); 
                    stage.show();


                } catch (IOException ex) {
                    Logger.getLogger(FXMLInicioSuperAdminController.class.getName()).log(Level.SEVERE, null, ex);
                }

                
            }
        };
        
        
        return handler;
    
    } 
    
    
    
}
