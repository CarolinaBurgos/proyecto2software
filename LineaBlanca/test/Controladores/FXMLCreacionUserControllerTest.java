/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import java.net.URL;
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
public class FXMLCreacionUserControllerTest {
    
    public FXMLCreacionUserControllerTest() {
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
     * Test of verificarDatos method, of class FXMLCreacionUserController.
     */
    @Test
    public void testVerificarDatos() {
        MouseEvent event = null;
        FXMLCreacionUserController instance = new FXMLCreacionUserController();
        
        boolean result = instance.verificarDatos(event);
        assertFalse(result);
        
    }

    
}
