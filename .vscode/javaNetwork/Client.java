import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;


public class Client {
  static Socket sc;
public static void main(String args[]){
try{
   sc = new Socket("localhost",6500);
while(true){
  PrintWriter pr = new PrintWriter(sc.getOutputStream(),true);//Your message → Buffer → Network
  //If 2 param true is not passed then Your message → Buffer ❌ (stuck here)
 System.out.println("Start chat"); 
 Scanner sca = new Scanner(System.in);
 String chat = sca.nextLine(); 
  pr.println(chat);

  BufferedReader br = new BufferedReader(new InputStreamReader(sc.getInputStream()));
System.out.println("Reading Servers msg");
  String read = br.readLine();
  
  System.out.println("Here the reply from Server - "+ read);

  System.out.println("Do you want to continue chattinG - Y/N");
   PrintWriter pr1 = new PrintWriter(sc.getOutputStream(),true);
  String rep = sca.nextLine();
  pr1.println(rep);  
}
}
catch(Exception e){
e.printStackTrace();
}
  
}
  
}
