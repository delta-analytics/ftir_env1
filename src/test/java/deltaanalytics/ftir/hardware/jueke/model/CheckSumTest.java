/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package deltaanalytics.ftir.hardware.jueke.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author frank
 */
public class CheckSumTest {
    
    public CheckSumTest() {
    }
    
    /**
     * Test of calculateCRC16CCITT method, of class CheckSum.
     */
    @Test
    public void testCalculateCRC16CCITT() {
        System.out.println("calculateCRC16CCITT");
        byte[] buffer = {0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38, 0x39};  //123456789
        CheckSum instance = new CheckSum();
        int expResult = 12739;  // hex value 31c3 -> 2 bytes {31, c3}
        int result = instance.calculateCRC16CCITT(buffer);
        assertEquals(expResult, result);
    }
    

    /**
     * Test of checkForConsistency method, of class CheckSum.
     */
    @Test
    public void testCheckForConsistency1() {
        System.out.println("checkForConsistency1 all correct");
        String s = "02 1A 08 00 00 00 50 00 B5 0F A0 0F 10 04 00 00 00 00 B5 0F 00 00 67 03 D3 AB";
        s = s.replaceAll("\\s","");
        System.out.println("s= " + s);
        byte[] buffer = javax.xml.bind.DatatypeConverter.parseHexBinary(s);
        
        CheckSum instance = new CheckSum();
        boolean expResult = true;
       
        boolean result = instance.checkForConsistency(buffer);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of checkForConsistency method, of class CheckSum.
     */
    @Test
    public void testCheckForConsistency2() {
        System.out.println("checkForConsistency2 CRC wrong");
        String s = "02 1A 08 00 00 00 50 00 B5 0F A0 0F 10 04 00 00 00 00 B5 0F 00 00 67 03 D3 AA";
        s = s.replaceAll("\\s","");
        System.out.println("s= " + s);
        byte[] buffer = javax.xml.bind.DatatypeConverter.parseHexBinary(s);
        
        CheckSum instance = new CheckSum();
        boolean expResult = false;
       
        boolean result = instance.checkForConsistency(buffer);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testCheckForConsistency3() {
        System.out.println("checkForConsistency3 start byte wrong");
        String s = "01 1A 08 00 00 00 50 00 B5 0F A0 0F 10 04 00 00 00 00 B5 0F 00 00 67 03 8A AE";
        s = s.replaceAll("\\s","");
        System.out.println("s= " + s);
        byte[] buffer = javax.xml.bind.DatatypeConverter.parseHexBinary(s);
        
        CheckSum instance = new CheckSum();
        boolean expResult = false;
       
        boolean result = instance.checkForConsistency(buffer);
        assertEquals(expResult, result);
    } 
    
    @Test
    public void testCheckForConsistency4() {
        System.out.println("checkForConsistency4 end byte wrong");
        String s = "02 1A 08 00 00 00 50 00 B5 0F A0 0F 10 04 00 00 00 00 B5 0F 00 00 67 01 F3 E9";
        s = s.replaceAll("\\s","");
        System.out.println("s= " + s);
        byte[] buffer = javax.xml.bind.DatatypeConverter.parseHexBinary(s);
        
        CheckSum instance = new CheckSum();
        boolean expResult = false;
       
        boolean result = instance.checkForConsistency(buffer);
        assertEquals(expResult, result);
    }      
    
}
