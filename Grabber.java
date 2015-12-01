// from http://www.cafeaulait.org/course/week12/22.html
// adapted FJ 19.11.2015
import java.net.*;
import java.io.*;

/*
 * USE java Grabber "host address"... like services from http://httpbin.org/
 * USE java Grabber http://localhost:80/OpusCommand.htm?GET_VERSION (other Bruker commands dont work from command line)
*/
public class Grabber {

  public static void main(String[] args) {

    int port = 80;

    for (int i = 0; i < args.length; i++) {
      try {
         URL u = new URL(args[i]);
	   System.out.println("Path of URL  " + u.getPath());
	   System.out.println("File of URL  " + u.getFile());
	   System.out.println("Query of URL  " + u.getQuery());
	   System.out.println("Ref of URL  " + u.getRef());
	   System.out.println("UserInfo of URL  " + u.getUserInfo());

         if (u.getPort() != -1) port = u.getPort();
         if (!(u.getProtocol().equalsIgnoreCase("http"))) {
           System.err.println("Sorry. I only understand http.");
           continue;
         }
         Socket s = new Socket(u.getHost(), port);
         OutputStream theOutput = s.getOutputStream();
         // no auto-flushing
         PrintWriter pw = new PrintWriter(theOutput, false);
         // native line endings are uncertain so add them manually

         pw.print("GET " + u.getFile() + " HTTP/1.0\r\n");
         pw.print("Accept: text/plain, text/html, text/*\r\n");
         pw.print("\r\n");
         pw.flush();
         InputStream in = s.getInputStream();
         InputStreamReader isr = new InputStreamReader(in);
         BufferedReader br = new BufferedReader(isr);
         int c;
         while ((c = br.read()) != -1) {
           System.out.print((char) c);
         }
      }
      catch (MalformedURLException ex) {
        System.err.println(args[i] + " is not a valid URL");
      }
      catch (IOException ex) {
        System.err.println(ex);
      }

    }

  }

}
