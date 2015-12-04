package deltaanalytics.ftir.hardware.jueke.model;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * calculate checksum of data stream according to cyclic reduncy check (CRC-CCIIT 0x0000) 16 bit
 * @author frank
 */
public class CheckSum {
    private final int dataLength = 26;
    
    public int calculateCRC16CCITT(byte[] buffer) {
        System.out.println("class CheckSum: calculateCRC16CCITT = " + javax.xml.bind.DatatypeConverter.printHexBinary(buffer));
        int crc = 0x0000;          // initial value  0xFFFF makes CRC16-CCITT = 29b1 for 123456789 but we need 31c3
        int polynomial = 0x1021;   // 0001 0000 0010 0001  (0, 5, 12) FJ: 'divisor'
        byte[] bytes = new byte[24];
        try{
                String str = new String(buffer, "CP858");  // ASCII, UTF-8, CP858, CP1252
                //System.out.println("Base64 encode string of byte array => " + org.apache.commons.codec.binary.Base64.encodeBase64String(bytesArray1));
                bytes = str.getBytes("CP858");
        }catch( UnsupportedEncodingException e){
                 System.out.println("Unsupported character set");
        }
        // CRC routine
        for (byte b : bytes) {
            for (int i = 0; i < 8; i++) {
                boolean bit = ((b   >> (7-i) & 1) == 1);
                boolean c15 = ((crc >> 15    & 1) == 1);
                crc <<= 1;
                if (c15 ^ bit) crc ^= polynomial;
             }
        }
        // finally
        crc &= 0xffff;

        return crc;
    }
    
    /**
     * calculate cheksum with CRC16CCITT method
     * @param buffer 26 bytes with 2 crc bytes
     * @return 
     */
    public boolean checkForConsistency(byte[] buffer) {
        byte firstByte = 0x02;
        byte lastByte = 0x03;
        if (buffer.length != dataLength ){
            System.out.println("checkForConsistency not exactly 26 bytes of data available");
            return false;
        }else{
            int checksum = this.calculateCRC16CCITT(Arrays.copyOfRange(buffer, 0, 24));  // 0=inclusive, 24=exclusive
            if ( firstByte != buffer[0]) {
                System.out.println("start byte not correct - 02!=" + buffer[0]);
                return false;                
            } else if ( lastByte != buffer[23]) {
                System.out.println("end byte not correct - 03!=" + buffer[23]);
                return false;
            } else if (checksum == bytes2Int(new byte[] {buffer[25], buffer[24]})) {  // low, high
                System.out.println("CRC correct\n");
                return true;
            } else { 
                System.out.println("CRC not correct: calculated=" + checksum + " proof bytes=" + bytes2Int(new byte[] {buffer[25], buffer[24]}) + "\n");
                return false;
            }
        }
    }
    
    // helper method
    // note: dublicate from JuekeStatus, put to separate class?
    private int bytes2Int(byte[] bytes) {
        int c = ((bytes[1] & 0xFF) << 8) | (bytes[0] & 0xFF);   // int c= ((high & 0xFF) << 8) | (low & 0xFF);
        return c; 
    }    
    
}
