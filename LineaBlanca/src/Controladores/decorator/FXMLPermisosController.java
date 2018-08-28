/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores.decorator;

import Conexion.ConexionesDataBase;
import Controladores.Escenario;
import Controladores.FXMLInicioSuperAdminController;
import Modelo.Empleado;
import Modelo.Peticion;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Toshiba
 */
public class FXMLPermisosController extends FXMLInicioSuperAdminController implements Initializable {

    //treeview
    @FXML
    Accordion acPeticiones;

    private Connection conn;

    List<Peticion> pets;
    Map<Integer, TitledPane> localControl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ConexionesDataBase.conect();
        this.conn = super.getConn();
        this.pets = this.buscarPeticionesPendientes();
        Escenario sc = new Escenario();
        super.setSc(sc);
        localControl = new HashMap<>();
        acPeticiones.getPanes().addAll(verPeticiones(pets));

    }

    //cargar todas las peticiones al accordion
    public List<TitledPane> verPeticiones(List<Peticion> petcs) {

        List<TitledPane> p = new ArrayList<>();

        for (Peticion peticion : petcs) {
            TitledPane tp = new TitledPane();
            tp.setText("Peticion Nº " + peticion.getId());
            GridPane grid = new GridPane();
            grid.setVgap(4);
            grid.setPadding(new Insets(5, 5, 5, 5));
            grid.add(new Label("Empleado que solicitó permisos: "), 0, 0);
            grid.add(new Label(empleadoPorPeticion(peticion.getId_empleado()).getNombre()), 1, 0);
            grid.add(new Label("ID de la compra a modificar: "), 0, 1);
            grid.add(new Label(Integer.toString(peticion.getId_venta())), 1, 1);
            grid.add(new Label("Razón del cambio: "), 0, 2);
            TextArea ta = new TextArea(peticion.getRazon_cambio());
            ta.setMinHeight(20);
            ta.setMaxHeight(60);
            ta.setEditable(false);
            grid.add(ta, 1, 2);
            grid.add(new Label(), 0, 3);
            grid.add(new Label(), 1, 3);
            Button aprobar = new Button("Aprobar Peticion Nº " + Integer.toString(peticion.getId()));
            aprobar.setOnMouseClicked(e -> aprobarPeticion(e));
            Button rechazar = new Button("Rechazar Peticion Nº " + Integer.toString(peticion.getId()));
            rechazar.setOnMouseClicked(e -> rechazarPeticion(e));
            grid.add(aprobar, 0, 4);
            grid.add(rechazar, 1, 4);
            tp.setContent(grid);
            localControl.put(peticion.getId(), tp);
            p.add(tp);
        }

        return p;
    }

    public Empleado empleadoPorPeticion(String id_empleado) {

        Empleado ret = new Empleado();
        try (Statement st = this.conn.createStatement()) {
            String empleado_query = "SELECT num_cedula, nombres, apellidos, usuario "
                    + "	FROM \"LBSASQL\".\"Empleado\" where num_cedula = '" + id_empleado + "';";

            try (ResultSet rs = st.executeQuery(empleado_query)) {

                while (rs.next()) {

                    String id = rs.getString("num_cedula");
                    String nombre = rs.getString("nombres");
                    String apellido = rs.getString("apellidos");
                    String user = rs.getString("usuario");

                    ret.setUserName(user);
                    ret.setNombre(nombre + ", " + apellido);
                    ret.setId_entidad("num_cdula");

                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(FXMLPermisosController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;

    }

    @FXML

    public void rechazarPeticion(MouseEvent event) {

        Button btn = (Button) event.getSource();
        int rem = removerPeticion(btn.getText());
        Alert alerta = modificarPeticion(rem, false);
        alerta.showAndWait();
    }

    @FXML

    public void aprobarPeticion(MouseEvent event) {

        Button btn = (Button) event.getSource();
        int rem = removerPeticion(btn.getText());
        Alert alerta = modificarPeticion(rem, true);
        alerta.showAndWait();

    }

    private int removerPeticion(String buttonText) {

        int petition = -1;

        String[] splitText = buttonText.split(" ");

        try {
            System.out.println(splitText[3]);
            petition = Integer.valueOf(splitText[3]);
            acPeticiones.getPanes().remove(localControl.get(petition));

            if (acPeticiones.getPanes().isEmpty()) {
                TitledPane npt = new TitledPane();
                npt.setContent(new Label("Todas las peticiones han sido contestadas."));
                acPeticiones.getPanes().add(npt);
            }

        } catch (NumberFormatException e) {

            System.out.println(e.toString());

        }

        return petition;
    }

    public Alert modificarPeticion(int id, boolean fue_aceptado) {

        Alert alert;

        String header;

        if (fue_aceptado == true) {
            header = "aceptada";
        } else {
            header = "rechazada";
        }

        try {

            String peticion_query = "UPDATE \"LBSASQL\".\"Peticion_modif_venta\"\n"
                    + "	SET aprobacion_pendiente=?, peticion_aceptada=?,"
                    + "fecha_actualizacion=current_timestamp\n"
                    + "	WHERE id_peticion=" + id + ";";
            try (PreparedStatement st = this.conn.prepareStatement(peticion_query)) {
                st.setBoolean(1, false);
                st.setBoolean(2, fue_aceptado);
                st.executeUpdate();

                alert = super.getSc().alertaGenerica("Estado de la peticion", "Los cambios han ido guardados con éxito.", "La peticion ha sido " + header, Alert.AlertType.INFORMATION);

                return alert;
            }

        } catch (SQLException ex) {
            Logger.getLogger(FXMLPermisosController.class.getName()).log(Level.SEVERE, null, ex);
            alert = super.getSc().alertaGenerica("SQL Exception", ex.toString(), "Excepcion", Alert.AlertType.ERROR);
            alert.showAndWait();
        }
        return alert;
    }

}
