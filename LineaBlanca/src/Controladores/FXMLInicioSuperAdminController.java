/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Conexion.ConexionesDataBase;
import Constantes.Constantes;
import Modelo.Peticion;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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

/**
 * FXML Controller class
 *
 * @author user
 */
public class FXMLInicioSuperAdminController extends ConexionesDataBase implements Initializable {

    private Connection conn;

    private Escenario sc;
    private JFXSnackbar sb;

    @FXML

    private AnchorPane rootPane;

    @FXML
    private Button BtnLogOut, permisos, productos, ventas, usuarios, inventario, clientes;

    private Stage perm_stage, prod_stage, users_stage, inven_stage, ventas_stage, clientes_stage;

    private int numpet;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ConexionesDataBase.conect();
        this.conn = super.getConn();
        numpet = 0;
        sc = new Escenario();
        mostrarPeticiones();
        perm_stage = new Stage();
        prod_stage = new Stage();
        users_stage = new Stage();
        inven_stage = new Stage();
        ventas_stage = new Stage();
        clientes_stage = new Stage();

    }

    @FXML
    public void logOut(MouseEvent event) {

        sc.cambioEscenaActual(event, Constantes.LOGIN_HEIGHT, Constantes.LOGIN_WIDTH, "/Views/FXMLLogin.fxml");
        perm_stage.close();
        prod_stage.close();
        users_stage.close();
        inven_stage.close();
        ventas_stage.close();
    }

    @FXML
    public void crearProducto() {

        prod_stage = sc.abrirNuevaVentana("Administración de Productos", "/Views/FXMLRegistrarProductos.fxml");

    }

    @FXML
    public void goToClientes() {

        prod_stage = sc.abrirNuevaVentana("Clientes", "/Views/FXMLBusquedaGenerica.fxml");

    }

    @FXML
    public void goToUsers() {

        users_stage = sc.abrirNuevaVentana("Administracion de usuarios", "/Views/FXMLCreacionUser.fxml");

    }

    @FXML
    public void inventario() {

        inven_stage = sc.abrirNuevaVentana("Búsqueda en inventario", "/Views/FXMLBusquedaGenerica.fxml");

    }

    private List<Peticion> buscarPeticionesPendientes() {

        List<Peticion> p = new ArrayList<>();
        try (Statement smnt = this.conn.createStatement()) {

            String query = "SELECT id_peticion, id_empleado, id_venta, aprobacion_pendiente, peticion_aceptada, razon_modificacion\n"
                    + "	FROM \"LBSASQL\".\"Peticion_modif_venta\"   WHERE aprobacion_pendiente = 'true';";

            try (ResultSet rs = smnt.executeQuery(query)) {
                while (rs.next()) {

                    p.add(new Peticion(rs.getInt("id_peticion"),
                            rs.getString("id_empleado"),
                            rs.getInt("id_venta"),
                            rs.getBoolean("aprobacion_pendiente"),
                            rs.getBoolean("peticion_aceptada"),
                            rs.getString("razon_modificacion")
                    ));
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(FXMLInicioSuperAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return p;

    }

    private EventHandler abrirPeticiones(JFXSnackbar bar) {

        EventHandler handler = new EventHandler() {

            @Override
            public void handle(Event event) {
                bar.unregisterSnackbarContainer(rootPane);
                perm_stage = sc.abrirNuevaVentana("Peticiones", "/Views/FXMLPermisos.fxml");

            }
        };

        return handler;

    }

    @FXML
    public void mostrarPeticiones() {

        List<Peticion> peticiones = buscarPeticionesPendientes();
        numpet = peticiones.size();

        String mensaje = "Bienvenido, tiene " + numpet + " peticion(es) pendiente(s)";
        String accion = "Abrir";

// configurando notificaciones
        sb = sc.notificacionSnackbar(rootPane);

        if (numpet > 0) {
            sb.show(mensaje, accion, abrirPeticiones(sb));
        } else {
            sb.show("Bienvenido, no tiene ninguna notificación por el momento.", 5000);
        }

    }

    @FXML
    public void abrirVentas(MouseEvent event) {

        ventas_stage = sc.abrirNuevaVentana("Ventas", "/Views/FXMLBusquedaGenerica.fxml");

    }

    public Escenario getSc() {
        return sc;
    }

    public void setSc(Escenario sc) {
        this.sc = sc;
    }

}
