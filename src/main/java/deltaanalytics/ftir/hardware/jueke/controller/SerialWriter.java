package deltaanalytics.ftir.hardware.jueke.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

/**
 * Output to serial port; Sends a command to the Jüke board (9 bytes)
 * @author frank
 */
public class SerialWriter implements Runnable {
    OutputStream out;

    public SerialWriter ( OutputStream out ) {
        this.out = out;
    }

    @Override
    public void run () {
        try {                
            //while ( continueInput() == 1 ) {
                JuekeCommandService service = new JuekeCommandService();
                byte[] cmd = service.getBytesArrayToWhiteCell();
                // write the 9 bytes to serial connection  (Jüke board)             
                this.out.write(cmd);
            //}                
        } catch ( IOException e ) {
            e.printStackTrace();
            System.exit(-1);
        }            
    }
    
    /*
      User should trigger each write = SerialWriter.run() process through action (button?) in GUI
    */
    private int continueInput(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Serial Writer: We now build a command to send to the Jueke board, continue? 0=no, 1=yes");
        int cmd = scanner.nextInt();
        return cmd;
    }
}  
