package deltaanalytics.ftir.hardware.jueke.model;

/**
 * collect all status information from Jüke board into hash array?
 * store temperature and pressure with time stamp to be able to use for calculation
 * @author frank
 */
public class JuekeStatus {
      
    // argument b1 = byte array of 26 bytes from Jüke board
    public void dataFromJueke(byte[] b1) {
        System.out.println("all bytes interpreted ...");
        System.out.println("start = " + (byte)b1[0]);  // 1st byte 0x02
        System.out.println("number of bytes = " + (byte)b1[1]);  // 2nd byte 0x1A
        System.out.println("ControllerStatus = " + (byte)b1[2]);  // 3rd + 4th byte, only 3rd is used 0x08, 4th is zero 0x00
        controllerStatus(byteArray2BitArray(new byte[] {(byte)b1[2], (byte)b1[3]}));
        System.out.println("ErrorFlags = " + (byte)b1[4]);  // 5th + 6th byte, only 5th is used 0x00, 6th is zero 0x00
        errorCode((new Byte((byte)b1[4])).intValue());  // 0x00, 0x01, 0x32, 0x64
        System.out.println("ValveStatus = " + (byte)b1[6]);  // 7th byte 0x50
        valveStatus(byteArray2BitArray(new byte[] {(byte)b1[6]}));  // valve 5 an 7 on
        System.out.println("  PowerHeater = " + (byte)b1[7] + ", unsigned " + b1[7] );  // 8th byte ((int)0x00) & 0xFF 
        System.out.println("  ActualTempHeater = " + bytes2Int(new byte[] {(byte)b1[8], (byte)b1[9]})/100.0 + " °C"); // 9th + 10th byte 0xB5 0x0F
        System.out.println("  SetpointHeater = " + bytes2Int(new byte[] {(byte)b1[10], (byte)b1[11]})/100.0 + " °C"); // 11th + 12th byte 0xA0 0x0F
        System.out.println("  ActualPressureCell = " + bytes2Int(new byte[] {(byte)b1[12], (byte)b1[13]}) + " mbar"); // 13th + 14th byte 0x10 0x04
        System.out.println("  SetpointPressure = " + bytes2Int(new byte[] {(byte)b1[14], (byte)b1[15]}) + " mbar"); // 15th + 16th byte 0x00 0x00
        //System.out.println("reserve byte 17 = " + (byte)b1[16]);  // byte 0x00
        System.out.println("  PumpPower = " + (byte)b1[17] + ", unsigned " + b1[17] );  // 18th byte ((int)0x00) & 0xFF
        System.out.println("  TempPT100_1 = " + bytes2Int(new byte[] {(byte)b1[18], (byte)b1[19]})/100.0 + " °C"); // 19th + 20th byte 0xB5 0x0F
        System.out.println("  TempPT100_2 = " + bytes2Int(new byte[] {(byte)b1[20], (byte)b1[21]})/100.0 + " °C"); // 21st + 22nd byte 0x00 0x00
        System.out.println("counter = " + (byte)b1[22]);  // 23rd byte 0x67
        System.out.println("end = " + (byte)b1[23]);  // 24th byte 0x03
        int crc = bytes2Int(new byte[] {(byte)b1[25], (byte)b1[24]});
        System.out.println("checksum hex => " + Integer.toHexString(crc));  // 25th and 26th byte
        (new CheckSum()).checkForConsistency(b1);
        System.out.println("...all bytes interpreted end");    
    }

    private void controllerStatus(boolean[] bits) {
        System.out.println("  Status of pump = " + bits[0] + " = " + (bits[0] == true ? "Pump Power > 0" : "off"));
        //System.out.println("  Status of Reserve Out = " + bits[1] + " = " + (bits[1] == true ? "on" : "off"));  // not used!
        System.out.println("  Pressure regulation active? = " + bits[2] + " = " + (bits[2] == true ? "active" : "not active"));
        System.out.println("  Temperature of Heater ok (setpoint reached)? = " + bits[3] + " = " + (bits[3] == true ? "ready" : "not ready"));
        System.out.println("  Heater regulation active? = " + bits[4] + " = " + (bits[4] == true ? "active" : "not active"));
    }

    private void errorCode(int value) {
        switch(value){
        case 0:
            System.out.println("  no error");
            break;
        case 1:
            System.out.println("  μC Error");
            break;
        case 50:
            System.out.println("  Pressure sensor error (PIRC1)");
            break;
        case 100:
            System.out.println("  Temperature sensor error");
            break;
        } 
    }

    private void valveStatus(boolean[] bits) {
        System.out.print("  ");
        int maxValve = 8;
        for (int i = 1; i < maxValve; i++) {
            System.out.print("valve " + (maxValve-i) + " = " + (bits[i] == true ? "on" : "off") + "; ");
        }
        System.out.println();
    }
    
    // helper methods, put into separate class?
    private boolean[] byteArray2BitArray(byte[] bytes) {
        boolean[] bits = new boolean[bytes.length * 8];
        for (int i = 0; i < bytes.length * 8; i++) {
            if ((bytes[i / 8] & (1 << (7 - (i % 8)))) > 0)
                bits[i] = true;
        }
        return bits;
    }
    
    private int bytes2Int(byte[] bytes) {
        int c = ((bytes[1] & 0xFF) << 8) | (bytes[0] & 0xFF);   // int c= ((high & 0xFF) << 8) | (low & 0xFF);
        return c; 
    }

}
