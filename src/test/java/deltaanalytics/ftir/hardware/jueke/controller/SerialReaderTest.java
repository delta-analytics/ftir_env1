/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package deltaanalytics.ftir.hardware.jueke.controller;

import gnu.io.SerialPortEvent;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 *
 * @author frank
 */
public class SerialReaderTest {
    
    public SerialReaderTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of serialEvent method, of class SerialReader.
     */
    @Test
    public void testSerialEvent() {
        System.out.println("SerialReaderTest serialEvent mocked");
        
        InputStream mockStream = mock(InputStream.class);
        SerialReader instance = new SerialReader(mockStream);  
        
        SerialPortEvent mockEvent = mock(SerialPortEvent.class);

        instance.serialEvent(mockEvent);
    }
    
    /**
     * Test of serialEvent method, of class SerialReader.
     */
    @Test
    public void testSerialEvent2() {
        System.out.println("SerialReaderTest 2 serialEvent with 26 bytes");
        
	byte[] b1 = new byte[] {(byte)0x02, (byte)0x1A, (byte)0x08, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x50, (byte)0x00, 
				(byte)0xB5, (byte)0x0F, (byte)0xA0, (byte)0x0F, (byte)0x10, (byte)0x04, (byte)0x00, (byte)0x00,
				(byte)0x00, (byte)0x00, (byte)0xB5, (byte)0x0F, (byte)0x00, (byte)0x00, (byte)0x67, (byte)0x03,
                                (byte)0xD3, (byte)0xAB};
        
        InputStream myIS = new ByteArrayInputStream(b1); 
        SerialReader instance = new SerialReader(myIS);  
        
        SerialPortEvent mockEvent = mock(SerialPortEvent.class);

        instance.serialEvent(mockEvent);
    }    
    
    /**
     * Test of serialEvent method, of class SerialReader.
     */
    @Test
    public void testSerialEvent3() {
        System.out.println("SerialReaderTest 3 serialEvent with 3*26 bytes");

        String s1 = "02 1A 08 00 00 00 50 00 B5 0F A0 0F 10 04 00 00 00 00 B5 0F 00 00 67 03 D3 AB";
        String s2 = "02 1A 10 00 00 00 00 00 BB 0A 28 0A 43 07 88 13 00 00 BB 0A 00 00 5A 03 B6 08";
        String s3 = "02 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 03 5E 65";
        
        String s = s1 + s2 + s3;
        
        s = s.replaceAll("\\s","");
        System.out.println("s= " + s);
        byte[] buffer = javax.xml.bind.DatatypeConverter.parseHexBinary(s);
        
        InputStream myIS = new ByteArrayInputStream(buffer); 
        SerialReader instance = new SerialReader(myIS);  
        
        SerialPortEvent mockEvent = mock(SerialPortEvent.class);

        instance.serialEvent(mockEvent);
        instance.serialEvent(mockEvent);
        instance.serialEvent(mockEvent);
    }    
    
}
