/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelo.Empleado;
import java.net.URL;
import java.sql.Connection;
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
public class FXMLLoginControllerTest {
    
    public FXMLLoginControllerTest() {
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
     * Test of getConnection method, of class FXMLLoginController.
     */
    @Test
    public void testGetConnection() {
        FXMLLoginController instance = new FXMLLoginController();
        Connection result = instance.getConnection();
        assertNull(result);
    }

    /**
     * Test of requestUser method, of class FXMLLoginController.
     */
    @Test
    public void testRequestUser() {
        
        String user = "";
        String psswd = "";
        FXMLLoginController instance = new FXMLLoginController();
        Connection conn = instance.getConn();
        String expResult = null;
        String result = instance.requestUser(conn, user, psswd);
        assertEquals(expResult, result);

    }


    /**
     * Test of getEmpleado method, of class FXMLLoginController.
     */
    @Test
    public void testGetEmpleado() {
        FXMLLoginController instance = new FXMLLoginController();
        Empleado result = instance.getEmpleado();
        assertNull(result);
        
    }

    
}
