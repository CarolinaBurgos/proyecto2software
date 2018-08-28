/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Constantes.Constantes;
import Modelo.Articulo;
import Modelo.ArticuloLineaBlanca;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author user
 */
public class FXMLBusquedaGenericaController extends ControlBusqueda implements Initializable {

    @FXML
    private ComboBox ComboElectBus;
    private ComboBox ComboElectCat;
    private Button BtnBuscar, BtnSalir;
    private TextField TxtNombre, TxtDescripcion;
    private TableView TableContent;
    private Tab TabArticulos, TabUsers, TabClientes;
    private TextField UserCI, UserNombre, UserCargo, TxtUser, UserMail, UserDireccion, UserApellido;
    private Button ClientesBuscar;
    private TextField ClientesCI, ClientesNombre, ClientesDireccion, ClientesMail;
    @FXML
    private TableColumn<Articulo, String> colCo, colDe, colCa, colMarca, colConsumo,
            colColor, colPrecio, colStock;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.connectar();
        this.setAccordingToPermissions();
        this.permiso = FXMLLoginController.user.getPermiso();
        this.sc = new Escenario();
        this.initializeTable();
        this.initializacomboBox();
        this.initializeTextField();
    }

    private void setAccordingToPermissions() {

        if (permiso.equalsIgnoreCase("Vendedor")
                || permiso.equalsIgnoreCase("Gerente")) {

            this.TabUsers.setDisable(true);
        }
    }

    private void initializeTable() {
        this.colCa.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        this.colConsumo.setCellValueFactory(new PropertyValueFactory<>("consumoElectrico"));
        this.colCo.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.colColor.setCellValueFactory(new PropertyValueFactory<>("monot"));
        this.colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        this.colDe.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        this.colStock.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        this.colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio_sin_iva"));
    }

    private void initializacomboBox() {
        this.ClientesCI = new TextField();
        this.UserCI = new TextField();
        this.ComboElectBus = new ComboBox();
        this.ComboElectCat = new ComboBox();
        this.ComboElectBus.getItems().removeAll(this.ComboElectBus.getItems());
        this.ComboElectBus.getItems().addAll("categoria", "descripcion", "nombre");
        this.ComboElectCat.getItems().removeAll(this.ComboElectCat.getItems());
        this.ComboElectCat.getItems().addAll("lavadora", "refrigeradora", "cocina");
        this.TxtNombre = new TextField();
        this.TxtDescripcion = new TextField();
        this.ComboElectCat.setDisable(true);
        this.TxtNombre.setDisable(true);
        this.TxtDescripcion.setDisable(true);
    }

    public void BuscarClientes(MouseEvent event) {
        if(!this.ClientesCI.getText().equals("")){
        try (PreparedStatement proc = conn.prepareStatement("select * from BuscarCliente(?)")) {
            proc.setString(1, this.ClientesCI.getText());
            try (ResultSet rs = (ResultSet) proc.executeQuery()) {
                if (rs.next()) {
                    this.ClientesNombre.setText(rs.getString(1));
                    this.ClientesDireccion.setText(rs.getString(2));
                    this.ClientesMail.setText(rs.getString(3));
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(FXMLBusquedaGenericaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }

    public void BuscarUsers(MouseEvent event) {
        if(!UserCI.getText().equals("")){
        try (PreparedStatement proc = conn.prepareStatement("select * from BuscarEmpleadoUsuario(?)")) {
            proc.setString(1, this.UserCI.getText());
            try (ResultSet rs = (ResultSet) proc.executeQuery()) {
                if (rs.next()) {
                    this.UserCargo.setText(rs.getString(6));
                    this.UserDireccion.setText(rs.getString(4));
                    this.UserMail.setText(rs.getString(5));
                    this.UserNombre.setText(rs.getString(1));
                    this.TxtUser.setText(rs.getString(3));
                    this.UserApellido.setText(rs.getString(2));
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(FXMLBusquedaGenericaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }

    public String definirBusqueda(MouseEvent event) {
        String query_resultSet = "";
        String selection = (String) this.ComboElectBus.getSelectionModel().getSelectedItem();
        if (selection.startsWith("categoria")) {
            this.ComboElectCat.setDisable(false);
            query_resultSet = "SELECT * from BuscarArticuloCategoria(?)";
        }
        if (selection.startsWith("descripcion")) {
            this.TxtDescripcion.setDisable(false);
            query_resultSet = "SELECT * from BuscarArticuloDescripcion(?)";
        }
        if (selection.startsWith("nombre")) {
            this.TxtNombre.setDisable(false);
            query_resultSet = "SELECT * from BusquedaArticuloNombre(?)";
        }

        return query_resultSet;
    }

    public void salir(MouseEvent event) {

        String view = returnView(this.permiso);
        sc.cambioEscenaActual(event, Constantes.AD_HEIGHT, Constantes.AD_WIDTH, "/Views/FXMLInicio" + view + ".fxml");

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

    private void initializeTextField(){
        this.TxtDescripcion = new TextField();
        this.TxtNombre = new TextField();
        this.TxtUser = new TextField();
        this.ClientesCI = new TextField();
        this.ClientesDireccion=new TextField();
        this.ClientesMail=new TextField();
        this.ClientesNombre=new TextField();
        this.UserNombre=new TextField();
        this.UserMail=new TextField();
        this.UserDireccion=new TextField();
        this.UserCargo=new TextField();
        this.UserApellido=new TextField();
        this.UserCI=new TextField();
    }
    
    public void FillTablesArticulo(MouseEvent event) {
        ObservableList<ObservableList> data = FXCollections.observableArrayList();
        String query = this.definirBusqueda(event);
        if (!query.equals("")) {
            try (PreparedStatement proc = conn.prepareStatement(query)) {
                try (ResultSet rs = (ResultSet) proc.executeQuery()) {
                    ObservableList<Articulo> row = FXCollections.observableArrayList();
                    while (rs.next()) {

                        ArticuloLineaBlanca art = new ArticuloLineaBlanca(Integer.parseInt(rs.getString(0)),
                                rs.getString(1), Double.parseDouble(rs.getString(8)),
                                rs.getString(3), Color.valueOf(rs.getString(4)), Integer.parseInt(rs.getString(7)));
                        row.add(art);
                        this.TableContent.setItems(data);

                    }
                }

            } catch (SQLException ex) {
                Logger.getLogger(FXMLInicioVendedorController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
