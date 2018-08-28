/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lineablanca;

import Conexion.ConexionesDataBase;
import static java.lang.System.exit;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author user
 */
public class LineaBlanca extends Application {

    @Override
    public void start(Stage stage) throws Exception {
       
        Parent root = FXMLLoader.load(getClass().getResource("/Views/FXMLLogin.fxml"));
        //stage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(root);
        scene.getStylesheets().add("CascadeStyleSheets/Views.css");
        stage.setScene(scene);
        stage.setOnCloseRequest(value -> cerrar());
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        launch(args);
        
    }
    
    public void cerrar(){
        Platform.exit();
        System.exit(0);
    
    }
}
