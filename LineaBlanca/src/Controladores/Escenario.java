/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import com.jfoenix.controls.JFXSnackbar;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Toshiba
 */
public class Escenario {

    public Escenario() {
    }
        
    public boolean cambioEscenaActual(MouseEvent event, double alto, double ancho, String ruta){
        
        boolean state = false;
        
        try {
            
            Node n = (Node) event.getSource();
            Stage currentStage = (Stage)n.getScene().getWindow();
            currentStage.setHeight(alto);
            currentStage.setWidth(ancho);
            n.getScene().setRoot(FXMLLoader.load(getClass().getResource(ruta)));
            state=true;
            return state;
            
        } catch (IOException ex) {
            this.alertaGenerica("IOException", ex.toString(), "IO Exception", Alert.AlertType.ERROR).showAndWait();
            Logger.getLogger(Escenario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return state;
}
    
    public Stage abrirNuevaVentana(String titulo, String ruta) {
        
        Stage currentStage = new Stage();     
        
        try {
            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(ruta));
            Parent root1 = (Parent) fxmlLoader.load();
            currentStage.setTitle(titulo);
            currentStage.setScene(new Scene(root1));
            currentStage.show();
            
            return currentStage;
            
        } catch (IOException ex) {
            Logger.getLogger(Escenario.class.getName()).log(Level.SEVERE, null, ex);
            this.alertaGenerica("IOException", ex.toString(), "IO Exception", Alert.AlertType.ERROR).showAndWait();
        }
        
        return currentStage;
    }
    
    public JFXSnackbar notificacionSnackbar(AnchorPane rootPane){
        
                         //configurando notificaciones
         JFXSnackbar sb= new JFXSnackbar(rootPane);
         sb.setLayoutX(rootPane.getHeight()/2);
         sb.setLayoutY(rootPane.getWidth()/2);
         

   
        return sb;
    
    }
    
    public Alert alertaGenerica(String titulo, String contenido, String encabezado, Alert.AlertType tipo){
    
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(encabezado);
        alerta.setContentText(contenido);
     
        return alerta;
    }
}
