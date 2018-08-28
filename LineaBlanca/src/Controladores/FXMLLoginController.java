/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import Modelo.Empleado;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author user
 */
public class FXMLLoginController extends ControlLogin implements Initializable {

    Escenario sc;
    public static Empleado user = null;

    public FXMLLoginController() {
    }

    @FXML
    AnchorPane root;
    @FXML
    private TextField lblUser;

    @FXML
    private TextField lblPass;

    @FXML
    private Button BtnClick;

    private Empleado emp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        super.connectar();
        sc = new Escenario();

    }

    public Connection getConnection() {
        return super.getConn();
    }

    public void Conectarse(MouseEvent event) throws IOException {
        String usr = lblUser.getText();
        String pass = lblPass.getText();

        String requestedUser = this.requestUser(super.getConn(), usr, pass);
        requestedUser = this.returnView(requestedUser);
        boolean state = false;

        if (!usr.isEmpty() && !pass.isEmpty() && requestedUser != null) {
            state = true;
        }

        if (state) {
            try {

                sc.cambioEscenaActual(event, 720, 920, "/Views/FXMLInicio" + requestedUser + ".fxml");

            } catch (Exception e) {
                Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, e);
            }
        } else {

        }

    }

    public String requestUser(Connection conn, String user, String psswd) {
        try (Statement stmt = conn.createStatement()) {
            String sql = " SELECT * FROM \"LBSASQL\".\"Empleado\" where usuario='" + user + "' and contraseña='" + psswd + "'";

            try (ResultSet rs = stmt.executeQuery(sql)) {
                if (rs.next()) {
                    this.emp = this.setEmpleado(rs);
                    return rs.getString(8); //cargo
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario no registrado", "Error de ingreso", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    public Empleado setEmpleado(ResultSet rs) {
        try {
            user = new Empleado(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(7), rs.getString(8), new Date(rs.getTimestamp(9).getTime()), new Date(rs.getTimestamp(10).getTime()), Boolean.getBoolean(rs.getString(11)));
            user.toString();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    public Empleado getEmpleado() {
        return this.emp;
    }

    public String returnView(String permiso) {
        if (permiso == null) {
            return "";
        }
        String[] views = {"Vendedor", "Admin", "Gerente", "SuperAdmin"};
        for (String perm : views) {
            String perm1 = perm.toLowerCase();
            if (permiso.startsWith(perm1.substring(0, 3))) {
                return perm;
            }
        }
        return "";
    }

    public void acceptDialogue() {
        JOptionPane.showMessageDialog(null, "Se han guardado los datos exitosamente", "Ingreso Correcto", JOptionPane.INFORMATION_MESSAGE);
    }

    public void errorDialogue() {
        JOptionPane.showMessageDialog(null, "Problema con conexion", "Error de ingreso", JOptionPane.ERROR_MESSAGE);
    }

}
