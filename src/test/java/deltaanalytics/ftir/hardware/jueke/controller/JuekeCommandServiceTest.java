/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package deltaanalytics.ftir.hardware.jueke.controller;

import java.io.IOException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author frank
 */
public class JuekeCommandServiceTest {
    
    private static final boolean TEST = true;  // perform tests
    
    public JuekeCommandServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        JuekeSerialConnection connection = new JuekeSerialConnection();
        // JuekeCommandService uses static TEST variable from JuekeSerialConnection
        connection.performTests(TEST);         
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getBytesArrayToWhiteCell method, of class JuekeCommandService.
     * @throws java.io.IOException
     */
    @Test
    public void testGetBytesArrayToWhiteCellForSetPressure5000() throws IOException {
        System.out.println("getBytesArrayToWhiteCell");
 
        JuekeCommandService instance = new JuekeCommandService();
        // cmd = 11, pressure=5000 => 2, b, 88, 13, 00, 00, 3, 33, a4
        byte[] expResult = new byte[] {(byte)0x02, (byte)0x0b, (byte)0x088, (byte)0x13, (byte)0x00, (byte)0x00, (byte)0x03, (byte)0x33, (byte)0xa4};
        byte[] result = instance.getBytesArrayToWhiteCell();
        assertArrayEquals(expResult, result);
    }
    
}
