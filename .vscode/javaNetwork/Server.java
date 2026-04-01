import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.*;


public class Server {
static ServerSocket ssc;
static Socket sc;

  public static void main(String args[]){
  try{

  
  while (true) {
  
     ssc = new ServerSocket(6500);
System.out.println("Waiting for Client to Connect");
  sc = ssc.accept();
System.out.println("Signal is created");
InputStreamReader isr = new InputStreamReader(sc.getInputStream());
  BufferedReader br = new BufferedReader(isr);
  PrintWriter pr = new PrintWriter(sc.getOutputStream(),true);//true autoflush is imp because without that your data will be in buffer stuck
  System.out.println("Server is ok msg from client - "+ br.readLine());
  
  pr.println("Hello I am your Server");
  BufferedReader br1 = new BufferedReader(isr);
  System.out.println(br1.readLine());
  sc.close();
  ssc.close();
  
  
    
  }

  
    
  
  }
  catch(Exception e){}
 } 
}
