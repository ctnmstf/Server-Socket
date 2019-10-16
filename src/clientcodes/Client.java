
package clientcodes;

import java.io.*;
import java.net.*;


public class Client {
 
  public final static int socket_port = 300;   //Port    
  public final static String server = "localhost";  //Server IP
  public final static String
       file_to_received = "C:\\Users\\musta\\Desktop\\Alıcı\\Deneme.txt"; //Dosyanın geleceği onum 
                                                            
 
  public final static int file_size = 6022386; 
 
  public static void main (String [] args ) throws IOException {
    int bytesRead;
    int current = 0;
    FileOutputStream fos = null;
    BufferedOutputStream bos = null;
    Socket sock = null;
    try {
      sock = new Socket(server, socket_port);
      System.out.println("Bağlanıyor...");
 
      // receive file
      byte [] mybytearray  = new byte [file_size];
      InputStream is = sock.getInputStream();
      fos = new FileOutputStream(file_to_received);
      bos = new BufferedOutputStream(fos);
      bytesRead = is.read(mybytearray,0,mybytearray.length);
      current = bytesRead;
 
      do {
         bytesRead =
            is.read(mybytearray, current, (mybytearray.length-current));
         if(bytesRead >= 0) current += bytesRead;
      } while(bytesRead > -1);
 
      bos.write(mybytearray, 0 , current);
      bos.flush();
      System.out.println("File " + file_to_received
          + " downloaded (" + current + " bytes read)");
        System.out.println("Dosya Aktarıldı");
    }
    finally {
      if (fos != null) fos.close();
      if (bos != null) bos.close();
      if (sock != null) sock.close();
    }
  }
 
}

