package deltaanalytics.ftir.hardware.jueke.controller;

import deltaanalytics.ftir.hardware.jueke.model.CheckSum;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.slf4j.LoggerFactory;


/**
 * Build byte array of 9 bytes to send to Jüke board
 * Structure: Start  CMD  Data  Data  Data  Data  Stop  CRC(High) CRC(Low)
 * hex        (02h)  ID   data0 data1 data2 data3 (03h) CRC from bytes 1-7
 * JuekeSerialConnection must be initialized
 * @author frank
 */
public class JuekeCommandService {
    
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(JuekeCommandService.class);        
    private byte[] bytesArrayToWhiteCell = new byte[9];
    private Scanner scanner = null;
    private final boolean TEST;  // TEST = static variable in JuekeSerialConnection

    public byte[] getBytesArrayToWhiteCell() {
        return bytesArrayToWhiteCell;
    }
    
    public JuekeCommandService(){
        this.TEST = JuekeSerialConnection.TEST; 
        System.out.println("perform tests = " + this.TEST);
        if (!TEST) {
            Scanning();
        }
	//Start  CMD  Data  Data  Data  Data  Stop  CRC(High) CRC(Low)
	//(02h)  ID   0     1     2     3     (03h)
        int start = 2;
	int stop = 3;

	int cmd2WC = controllerByteToWhiteCell();  // choose one of 11 commands

	byte[] dataBytes = dataInput(cmd2WC);  // prepare the data bytes, according to the choosen command

	// byte array to send via serial connection without the 2 check sum bytes
	byte[] bytesArray = new byte[] {(byte)start, (byte)cmd2WC, (byte)dataBytes[0], (byte)dataBytes[1], (byte)dataBytes[2], (byte)dataBytes[3], (byte)stop};

	// calculate checksum byte array without checksum
	int crc = (new CheckSum()).calculateCRC16CCITT(bytesArray);

	System.out.println("CRC16-CCITT decimal = " + crc + " (hex => " + Integer.toHexString(crc) + ")\n");
	System.out.println("CRC16-CCITT low => " + Integer.toHexString(crc & 0xff));
	System.out.println("CRC16-CCITT high => " + Integer.toHexString((crc >> 8) & 0xff));

	// just for control
	System.out.println("9 bytes (high data byte first) => "   // note Integer has 32 bit
				+ Integer.toHexString(start) + ", " + Integer.toHexString(cmd2WC) + ", "
				+ String.format("%02X", dataBytes[0])  + ", " + String.format("%02X", dataBytes[1]) + ", "
				+ String.format("%02X", dataBytes[2]) + ", " + String.format("%02X", dataBytes[3]) + ", "
				+ Integer.toHexString(stop) + ", "
				+ Integer.toHexString((crc >> 8) & 0xff) + ", " + Integer.toHexString(crc & 0xff));

	// complete byte array to send via serial connection with check sum bytes
	bytesArrayToWhiteCell = new byte[] {(byte)start, (byte)cmd2WC, dataBytes[0], dataBytes[1], dataBytes[2], dataBytes[3],
						   (byte)stop, (byte)((crc >> 8) & 0xff), (byte)(crc & 0xff) };

	System.out.println("input to Jüke board = " + javax.xml.bind.DatatypeConverter.printHexBinary(bytesArrayToWhiteCell));    
        scanner.close();
    }
    
    private void Scanning(){
        scanner  = new Scanner(System.in, "UTF-8"); //.useDelimiter("\\n");
    }
    
    /*
       Used with TESTS because System.in does not work in JUNIT test case
    */
    private String[] getConfig() {
        String[] conf = new String[3];

        try {
            scanner = new Scanner(new File("config.txt"));
        } catch (FileNotFoundException ex) {
            LOGGER.error("JuekeSerialConnectionTest FileNotFoundException " + ex);
        }
        
        int i = 0;
        while(scanner.hasNext()){
           conf[i++] = scanner.nextLine();
        }

        return conf;
    }
      
    @SuppressWarnings("empty-statement")
    private int controllerByteToWhiteCell() { 
        System.out.println("Please choose one of the following options 1,2,3,4,5,10,11,12,13,14,15");
        System.out.println("  StartCom = 1; StopCom = 2; StartBootloader = 3");
        System.out.println("  SetValves = 4; SetPumpPower = 5");
        System.out.println("  SetPressureSetpoint = 11; StartPres.Regulation = 12; StopPres.Regulation = 13");
        System.out.println("  SetTempHeater = 10; StartHeat.Regulation = 14; StopHeat.Regulation = 15");
        
        String cmd = TEST ? getConfig()[0] : scanner.nextLine();
        
        System.out.println("controllerByteToWhiteCell: cmd=" + cmd);
        
	return Integer.parseInt(cmd);
    }

    private byte[] dataInput(int cmd) {
	byte[] data = new byte[] {(byte)0, (byte)0, (byte)0, (byte)0};

        switch(cmd){
        case 4:  // valves
	    System.out.println("  input sequence of 0's and 1's, length 7, starting at v7, for example 1000000 for v7=on, v1...v6=off");
            String s = TEST ? getConfig()[1] : scanner.next();
	    data[0] = Byte.parseByte(s, 2);
            break;
        case 5:  // pump
            System.out.println("  int value between 0 ... 100; 0 = stop pump");
	    int speed = TEST ? Integer.parseInt(getConfig()[1]) : scanner.nextInt();
	    data[0] = (byte)speed;
            break;
        case 10:  // temperature
            System.out.println("  input float value range 20 ... 60, decimal separation is ','");  // °C*100 = 2000 ... 6000 
	    float temp = TEST ? Float.parseFloat(getConfig()[1]) : scanner.nextFloat();
	    short xt = (short)(temp*100);  // convert to 16 bit and multiply by 100
	    data[0] = (byte)(xt & 0xff);
	    data[1] = (byte)((xt >> 8) & 0xff);
            break;
        case 11:  // pressure
            System.out.println("  input integer value range 1200 ... 7000");  // mbar
	    short pressure = TEST ? Short.parseShort(getConfig()[1]) : scanner.nextShort();
	    data[0] = (byte)(pressure & 0xff);
	    data[1] = (byte)((pressure >> 8) & 0xff);
            break;
        }
        System.out.println("dataInput as 4 hex numbers/data bytes= " + javax.xml.bind.DatatypeConverter.printHexBinary(data));
        
	return data;
    }
    
    
}
