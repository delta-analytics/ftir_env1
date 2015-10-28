/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package deltaanalytics.ftir.hardware.jueke.controller;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.slf4j.LoggerFactory;

/**
 * setup only works if sudo and socat are installed/working on host PC
 * @author frank
 */
public class JuekeSerialConnectionTest {
    
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(JuekeSerialConnectionTest.class);
    private static Process p1, p2, p3;
    private static String pid;
    private static final String password = "maurice";   // can't get it with scanner!
    private static final boolean TEST = true;  // perform tests
    
    public JuekeSerialConnectionTest() {
    }
   
    @BeforeClass
    public static void setUpClass() {    
        try {
            System.out.println("JuekeSerialConnectionTest open virtual ports");
            String[] cmd1 = {"/bin/bash",
                             "-c",
                             "echo " + password + "|sudo -S socat -d -d PTY,link=/dev/ttyS10 PTY,link=/dev/ttyS11"};
 
            p1 = new ProcessBuilder().command(cmd1).redirectErrorStream(true).start(); 
            p1.waitFor(50L, TimeUnit.MILLISECONDS);  // needed time to open PTY
            
            p1.getOutputStream().close(); // close stdin of child
            
            // get pid from this process to be able to kill it later; p1..destroy() only destroys the java process
            // note redirect errorstream for process builder above
            BufferedReader br = new BufferedReader(new InputStreamReader(p1.getInputStream()));
            String line = br.readLine();
            //System.out.println("line = "+line);
            if (line != null){
                pid = line.split("[\\[\\]]")[3];  // splits by [ or ] and takes 3rd occurance 
                System.out.println("pid= "+pid);
                
                String[] cmd2 = {"/bin/bash",
                                 "-c",
                                 "echo " + password + "| sudo -S chmod 777 /dev/ttyS10 /dev/ttyS11"};            

                p2 = new ProcessBuilder().command(cmd2).inheritIO().start();
                p2.waitFor(50L, TimeUnit.MILLISECONDS);  // needed to kill process p1
            }
                     
            br.close();
        } catch (IOException ex) {
            LOGGER.error("JuekeSerialConnectionTest IOException " + ex);
        }  catch (InterruptedException ex) {
            LOGGER.error("JuekeSerialConnectionTest InterruptedException " + ex);
        }
    }
    
    @AfterClass
    public static void tearDownClass() {
        System.out.println("JuekeSerialConnectionTest close virtual ports");
        String[] cmd = {"/bin/bash",
                         "-c",
                         "echo " + password + "| sudo -S kill " + pid};    
        if (pid != null){
            try {
                p3 = new ProcessBuilder().command(cmd).inheritIO().start();
                p3.waitFor(50L, TimeUnit.MILLISECONDS);   // needed to kill process p1
                p1.destroy();
                p2.destroy();            
                p3.destroy();
            } catch (IOException ex) {
                LOGGER.error("JuekeSerialConnectionTest IOException " + ex);
            }  catch (InterruptedException ex) {
                LOGGER.error("JuekeSerialConnectionTest InterruptedException " + ex);
            }
        }
    }

    /**
     * Test of listPorts method, of class JuekeSerialConnection.
     * @throws java.lang.Exception
     */
    @Test
    public void testListPorts() throws Exception {
        System.out.println("test listPorts");
        JuekeSerialConnection instance = new JuekeSerialConnection();
        instance.performTests(TEST);
        instance.listPorts();
        assertNotNull(instance.portList);  // serial ports availble!
    }

    /**
     * Test of getPortTypeName method, of class JuekeSerialConnection.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetPortTypeName() throws Exception {
        System.out.println("test getPortTypeName");
        int portType = CommPortIdentifier.PORT_SERIAL;
        JuekeSerialConnection instance = new JuekeSerialConnection();
        instance.performTests(TEST);
        String expResult = "Serial";
        String result = instance.getPortTypeName(portType);
        assertEquals(expResult, result);
    }

    /**
     * Test of connect method, of class JuekeSerialConnection.
     * @throws java.lang.Exception
     */
    @Test
    public void testConnect() throws Exception {
        System.out.println("test connect");
        String portName = "/dev/ttyS11";
        JuekeSerialConnection instance = new JuekeSerialConnection();
        instance.performTests(TEST);
        SerialPort serialPort = (SerialPort) instance.connect(portName);
        int[] a1 = new int[] {57600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE};
        int[] a2 = new int[] {serialPort.getBaudRate(), serialPort.getDataBits(), serialPort.getStopBits(),serialPort.getParity()};
        assertArrayEquals(a1,a2);
        assertNotNull( serialPort.getInputStream());
        assertNotNull( serialPort.getOutputStream());
    }
    
}
