/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author user
 */
public class FXMLBusquedaGenericaController extends FXMLLoginController implements Initializable {

    @FXML
    private ComboBox ComboElectBus;
    private ComboBox ComboElectCat;
    private Button BtnBuscar, BtnSalir;
    private TextField TxtNombre, TxtDescripcion;
    private TableView TableContent;
    private Tab TabArticulos, TabUsers, TabClientes;
    private TextField UserCI, UserNombre,UserCargo,TxtUser,UserMail,UserDireccion;
    private Button ClientesBuscar;
    private TextField ClientesCI, ClientesNombre, ClientesDireccion,ClientesMail;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        super.initialize(url, rb);
        this.setAccordingToPermissions();
    }    
    
    public void setAccordingToPermissions(){
        if(super.getEmpleado().getPermiso().equalsIgnoreCase("Vendedor")||
                super.getEmpleado().getPermiso().equalsIgnoreCase("Gerente")){
            this.TabUsers.setDisable(true);
        }
    }
    
    public void initializacomboBox(){
        this.ComboElectBus.getItems().removeAll(this.ComboElectBus.getItems());
        this.ComboElectBus.getItems().addAll("categoria", "descripcion", "nombre");
        this.ComboElectCat.getItems().removeAll(this.ComboElectCat.getItems());
        this.ComboElectCat.getItems().addAll("lavadora", "refrigeradora","cocina");
        this.ComboElectCat.setDisable(true);
        this.TxtNombre.setDisable(true);
        this.TxtDescripcion.setDisable(true);
    }
    
    public void BuscarClientes(MouseEvent event){
        
        Statement state;
        String query_llamada_procedure = "SELECT BuscarEmpleadoUsuario('"+this.ClientesCI.getText()+"')";
        try {
            state = super.getConnection().createStatement();
            ResultSet rs = state.executeQuery(query_llamada_procedure);
            this.FillTables(rs);
            if(rs.next())
                setearDatos(rs);
            
        } catch (SQLException ex) {
            Logger.getLogger(FXMLBusquedaGenericaController.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    public void setearDatos(ResultSet rs) throws SQLException{
        this.ClientesDireccion.setText(rs.getString(4));
        this.ClientesMail.setText(rs.getString(3));
        this.ClientesNombre.setText(rs.getString(2));
        
    }
    
    public void BuscarUsers(MouseEvent event){        
        Statement state;
        String query_llamada_procedure = "SELECT BuscarEmpleadoUsuario('"+this.UserCI.getText()+"')";
        try {
            state = super.getConnection().createStatement();
            ResultSet rs = state.executeQuery(query_llamada_procedure);
            this.FillTables(rs);
            if(rs.next())
                setearDatos(rs);
            
        } catch (SQLException ex) {
            Logger.getLogger(FXMLBusquedaGenericaController.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    public void setearDatosUser(ResultSet sr) throws SQLException{
        this.TxtUser.setText(sr.getString(4));
        this.UserCargo.setText(sr.getString(8));
        this.UserDireccion.setText(sr.getString(6));
        this.UserMail.setText(sr.getString(5));
        this.UserNombre.setText(sr.getString(2));
                                       
    }
    
    
    public void BuscarArticulos(MouseEvent event){
        Statement state;
        String query_llamada_procedure = this.definirBusqueda(event);
        try {
            state = super.getConnection().createStatement();
            ResultSet rs = state.executeQuery(query_llamada_procedure);
            this.FillTables(rs);
            if(rs.next())
                setearDatos(rs);
            
        } catch (SQLException ex) {
            Logger.getLogger(FXMLBusquedaGenericaController.class.getName()).log(Level.SEVERE, null, ex);
        }       
        
    }
    
    public String definirBusqueda(MouseEvent event){
        String query_resultSet ="";
        String selection = (String) this.ComboElectBus.getSelectionModel().getSelectedItem();
        if(selection.startsWith("categoria")){
            this.ComboElectCat.setDisable(false);
            query_resultSet = "SELECT BuscarArticuloCategoria('"+this.ComboElectCat.getSelectionModel().getSelectedItem()+"')";
        }
        if(selection.startsWith("descripcion")){
            this.TxtDescripcion.setDisable(false);
            query_resultSet ="SELECT BuscarArticuloDescripcion('"+this.TxtDescripcion.getText()+"')";
        }
        if(selection.startsWith("nombre")){
            this.TxtNombre.setDisable(false);
            query_resultSet = "SELECT BusquedaArticuloNombre('"+this.TxtNombre.getText()+"')";
        }
            
        return query_resultSet;
    }
    

    
    public void salir(MouseEvent event){
        String view =returnView(this.getEmpleado().getPermiso());
        try{
                Node n = (Node) event.getSource();
                n.getScene().setRoot(FXMLLoader.load(getClass().getResource("/lineablanca/FXMLInicio"+view+".fxml")));
            }catch(IOException e){
                System.out.println(e);
            }
    }
    
    public String returnView(String permiso){
        if(permiso==null)
            return "";
        String[] views = {"Vendedor", "Admin","Gerente", "SuperAdmin"};
        for(String perm: views){
            String perm1=perm.toLowerCase();
            if(permiso.startsWith(perm1.substring(0, 3))){
                return perm;}
        }
        return "";
    }
    
    
    public void FillTables(ResultSet rs){
        ObservableList<ObservableList> data = FXCollections.observableArrayList();
        
        try {
            while(rs.next()){
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                data.add(row);

            }
            this.TableContent.setItems(data);
            
        } catch (SQLException ex) {
            Logger.getLogger(FXMLInicioVendedorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
}
