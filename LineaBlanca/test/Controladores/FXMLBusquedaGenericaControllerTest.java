/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelo.Empleado;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.scene.input.MouseEvent;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author user
 */
public class FXMLBusquedaGenericaControllerTest {
    
    public FXMLBusquedaGenericaControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    /**
     * Test of returnView method, of class FXMLBusquedaGenericaController.
     */
    @Test
    public void testReturnView() {
        String permiso = "vendedor";
        FXMLBusquedaGenericaController instance = new FXMLBusquedaGenericaController();
        String expResult = "Vendedor";
        String result = instance.returnView(permiso);
        assertEquals(expResult, result);
    }
    
}
