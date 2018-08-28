/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

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
public class ContribuyenteRegistradoTest {
    
    public ContribuyenteRegistradoTest() {
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
     * Test of isEsContibEspecial method, of class ContribuyenteRegistrado.
     */
    @Test
    public void testIsEsContibEspecial() {
        System.out.println("isEsContibEspecial");
        ContribuyenteRegistrado instance = new ContribuyenteRegistrado("12658790458789","","",null,"",true);
        assertTrue(instance.isEsContibEspecial());
        
    }

    /**
     * Test of setEsContibEspecial method, of class ContribuyenteRegistrado.
     */
    @Test
    public void testSetEsContibEspecial() {
        boolean esContibEspecial = false;
        ContribuyenteRegistrado instance = new ContribuyenteRegistrado("12658790458789","","",null,"",true);
        instance.setEsContibEspecial(esContibEspecial);

    }

    /**
     * Test of validarID method, of class ContribuyenteRegistrado.
     */
    @Test
    public void testValidarID() {
        System.out.println("validarID");
        ContribuyenteRegistrado instance = new ContribuyenteRegistrado("1265879045809","","",null,"",true);
        assertTrue(instance.validarID());

    }

    
}
