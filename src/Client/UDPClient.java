package Client;

import Classes.Packet;
import Classes.TimeHistory;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UDPClient {
  public static void main(String[] args) {
    try {
      // args contain server hostname
      if(args.length < 1) {
    	  System.out.println("Usage: UDPClient <server host name>");
    	  System.exit(-1);
      }
   	  byte[] buffer = new byte[1024];
   	  //
   	  TimeHistory th = new TimeHistory("comp", "test", 23, 1,"Amp", 1, 2, 2);
   	  //
      InetAddress aHost = InetAddress.getByName(args[0]);
      int serverPort = 9876;
      DatagramSocket aSocket = new DatagramSocket();
      Scanner scan = new Scanner(System.in);
      String line = "";
      while(true) {
    	  System.out.println("Enter your request (+,-,?,!)|nick|...|");
    	  if(scan.hasNextLine())
    		  line = scan.nextLine();
    	  //
    	  byte[] data = Tools.serialize(th);
    	  //
       	  DatagramPacket request = new DatagramPacket(data, data.length, aHost, serverPort);
    	  aSocket.send(request);
    	  DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
    	  aSocket.receive(reply);
    	  //
    	  System.out.println("Reply: " + new String(reply.getData(), 0, reply.getLength()));;
    	  Packet read = null;
          try {
              read = (Packet) Tools.deserialize(reply.getData());
          } catch (ClassNotFoundException e) {
              e.printStackTrace();
          }
          //
          System.out.println(read);
      }
    } catch (SocketException ex) {
      Logger.getLogger(UDPClient.class.getName()).log(Level.SEVERE, null, ex);
    } catch (UnknownHostException ex) {
      Logger.getLogger(UDPClient.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
      Logger.getLogger(UDPClient.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

}