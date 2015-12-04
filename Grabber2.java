// from http://www.cafeaulait.org/course/week12/22.html
// adapted FJ 19.11.2015
import java.net.*;
import java.io.*;

/*
 * USE "java Grabber2"
 * uncomment one urlString, compile and run
*/
public class Grabber2 {

  public static void main(String[] args) {

      int port = 80;
	int file_id = 673;
	String sample = "TestSoft";
	String file_name = "TestSoft.0.dpt";
      //String urlString = "http://localhost:80/OpusCommand.htm?MeasureReference (0, {EXP='frank.xpm', XPP='C:\\OPUS_7.0.129\\XPM'})";
      String urlString = "http://localhost:80/OpusCommand.htm?MeasureSample (0, {EXP='frank.xpm', XPP='C:\\OPUS_7.0.129\\XPM', SNM='"+sample+"', NSS=16}";
	//String urlString = "http://localhost/OpusCommand.htm?SaveAs (["+file_id+"]:AB], {DAP='C:\\OPUS_7.0.129\\MEAS3', OEX='0', SAN='"+file_name+"', COF=64})";
	//String urlString = "http://localhost/OpusCommand.htm?Unload (["+file_id+"], {})";  // feed ID of file to unload in Bruker software
      try {
         URL url = new URL(urlString);  // take string
         URI uri = new URI(url.getProtocol(),  // make uri to get correct encoding for sending the request
			url.getUserInfo(),
			url.getHost(),
			url.getPort(),
			url.getPath(),
			url.getQuery(),  // do not encode here!!!
			url.getRef());  // 7 arguments
         url = uri.toURL();  // correct encoded URL

	   System.out.println("File of URL  " + url.getFile());  // we want this!
	   System.out.println("Path of URL  " + url.getPath());
	   System.out.println("Query of URL  " + url.getQuery());

         if (url.getPort() != -1)
		port = url.getPort();
         if (!(url.getProtocol().equalsIgnoreCase("http"))) {
           System.err.println("Sorry. I only understand http.");
           System.exit(1);
         }//if
	   // make socket and get output stream
         Socket soc = new Socket(url.getHost(), port);
         OutputStream theOutput = soc.getOutputStream();

         // use PrintWriter to write request to Bruker, no auto-flushing
         PrintWriter pw = new PrintWriter(theOutput, false);
         // native line endings are uncertain so add them manually
         pw.print("GET " + url.getFile() + " HTTP/1.0\r\n");   //url.getFile() is what we have to ask, compare output from above!
         pw.print("Accept: text/plain, text/html, text/*\r\n");
         pw.print("\r\n");
         pw.flush();
	   // get input stream to read answer from Bruker
         InputStream in = soc.getInputStream();
         InputStreamReader isr = new InputStreamReader(in);
         BufferedReader br = new BufferedReader(isr);
	   StringBuilder sb = new StringBuilder();
	   String line = null;
	   while ((line = br.readLine()) != null) {
	      sb.append(line + " ");
	   }//while
	   System.out.println("Answer from Bruker = " + sb.toString());
	   String[] answer = sb.toString().split(" ");
	   int i=0;  // for-loop for debugging purpose only
	   for (String str : answer) { 
	      System.out.println(i++ + ". " +str);
	   }//for
	   if (answer[0].equals("Error")) {
		System.out.println("Error in Opus Command Line Execution with ID: " + answer[answer.length-1]);
	   } else if (answer[0].equals("OK")) {
		if (answer[2].equals("0")) {
		    System.out.println("OK done!");
		} else if (answer[2].equals("1")) {
		    System.out.println("Sample Measurement done!");
		    System.out.println("File ID = " + answer[5]);
		    System.out.println("File Path = " + answer[3]);
		} else {
		    System.out.println("other service used!");
		}
	   } else {
		System.out.println(sb.toString());  // all answers which don't start with Error
	   }
	   pw.close();
	   soc.close();
      }//try
      catch (MalformedURLException ex) {
        System.err.println(urlString + " is not a valid URL");
      }//catch
      catch (URISyntaxException ex) {
        System.err.println(ex);
      }//catch
      catch (IOException ex) {
        System.err.println(ex);
      }//catch
  }//main

}//class
