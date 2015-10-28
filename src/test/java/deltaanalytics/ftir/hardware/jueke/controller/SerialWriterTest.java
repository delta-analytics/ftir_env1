/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package deltaanalytics.ftir.hardware.jueke.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.junit.AfterClass;
import org.junit.Test;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author frank
 */
public class SerialWriterTest {
    
    private static final boolean TEST = true;  // perform tests
    
    public SerialWriterTest() {
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
     * Test of run method, of class SerialWriter. Mocking
     */
    @Test
    public void testRun() {
        System.out.println("run");
        
        OutputStream mockStream = mock(OutputStream.class);
        SerialWriter instance = new SerialWriter(mockStream);
        instance.run();
    }
    
    /**
     * Test of run method, of class SerialWriter. Write to file
     * @throws java.io.FileNotFoundException
     */
    @Test
    public void testRun2() throws FileNotFoundException, IOException {
        System.out.println("run2");
        
        OutputStream os = new FileOutputStream("testWriter.bte");
        SerialWriter instance = new SerialWriter(os);
        instance.run();
        
        InputStream is = new FileInputStream("testWriter.bte");
        final int expected = 9;
        assertEquals(expected, is.available());
        
        // read what we wrote
        byte[] b = new byte[expected];
        is.read(b);
        System.out.println("read from test.txt = " + javax.xml.bind.DatatypeConverter.printHexBinary(b));
    }    
    
}
