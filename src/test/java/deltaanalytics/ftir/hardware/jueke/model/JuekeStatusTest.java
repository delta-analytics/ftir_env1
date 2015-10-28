/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package deltaanalytics.ftir.hardware.jueke.model;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author frank
 */
public class JuekeStatusTest {
    
    public JuekeStatusTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of dataFromJueke method, of class JuekeStatus.
     */
    @Test
    public void testDataFromJueke() {
        System.out.println("dataFromJueke");
        String s = "02 1A 10 00 00 00 00 00 BB 0A 28 0A 43 07 88 13 00 00 BB 0A 00 00 5A 03 B6 08";
        s = s.replaceAll("\\s","");
        System.out.println("s= " + s);        
        byte[] b1 = javax.xml.bind.DatatypeConverter.parseHexBinary(s);
        JuekeStatus instance = new JuekeStatus();
        instance.dataFromJueke(b1);
    }
    
}
