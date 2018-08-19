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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author user
 */
public class FXMLRegistrarProductosController extends FXMLLoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TextArea AreaDesc;
    @FXML
    private Button BtnDespliegue, BtnHome, BtnRegistro;
    @FXML
    private ColorPicker Chcolr; 
    @FXML
    private Pane PaneSlide;
    @FXML
    private TextField TxtCat,TxtElectrico,TxtId,TxtMar,TxtPrecioProv,TxtPrecio;
    @FXML
    private Label LblLenar;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        super.initialize(url, rb);
    }    
    
    public void registrarse(MouseEvent event){
        if(this.validarRegistro(event)){
            String query_stmt = this.prepararQuery();
            Statement stmt;
            try {
                stmt = super.getConn().createStatement();
                ResultSet rs = stmt.executeQuery(query_stmt);
                this.acceptDialogue();
            } catch (SQLException ex) {
                this.errorDialogue();
                Logger.getLogger(FXMLRegistrarProductosController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
    private boolean validarRegistro(MouseEvent event){
        if(this.TxtId.getText().equals("") || this.TxtCat.getText().equals("")||
                this.TxtMar.getText().equals("")||this.TxtElectrico.getText().equals("")
                ||this.TxtPrecioProv.getText().equals("")||this.TxtPrecio.getText().equals("")){
            this.LblLenar.setVisible(true);
            return false;
     }
     return true;
    }
    public String prepararQuery(){
        return  "INSERT INTO \"LBSASQL\".\"Articulo\"("
                    + "id_articulo, descripcion, categoria, marca, color, consumo_electrico, "
                    + "precio_cliente_sin_iva, costo_proveedor, reg_eliminado)"
                    + "	VALUES ("+this.TxtId.getText()+","+ this.AreaDesc.getText()
                    +","+this.TxtCat.getText()+","+this.TxtMar.getText()+","+
                    this.Chcolr.getValue().toString()+","+this.TxtElectrico.getText()+","+
                    this.TxtPrecio.getText()+","+this.TxtPrecioProv.getText()+","+false+")";
    }
    
    public void acceptDialogue(){
        JOptionPane.showMessageDialog(null, "Se han guardado los datos exitosamente", "Ingreso Correcto", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void errorDialogue(){
        JOptionPane.showMessageDialog(null, "Problema con conexion", "Error de ingreso", JOptionPane.ERROR_MESSAGE);
    }
    
    public void goBack(MouseEvent event){
        String permiso="Admin";
        if(super.getEmpleado().getPermiso().startsWith("super"))
            permiso = "SuperAdmin";
        
        try{
                Node n = (Node) event.getSource();
                n.getScene().setRoot(FXMLLoader.load(getClass().getResource("/lineablanca/FXMLOInicio"+permiso+".fxml")));
            }catch(IOException e){
                System.out.println(e);
            }
    }
    
}
