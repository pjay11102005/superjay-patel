import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Bank_Client {
  public static void main(String args[]){
    try{
      Socket sc = new Socket("localhost",6000);
  Scanner sc1 = new Scanner(System.in);
  System.out.println("Enter yopur acc id");
  int x = sc1.nextInt();
  PrintWriter pr = new PrintWriter(sc.getOutputStream(),true);
  BufferedReader br = new BufferedReader(new InputStreamReader(sc.getInputStream()));
  System.out.println("Sending data...");
  pr.println(x);
  String balance = br.readLine();
  double b = Double.parseDouble(balance);
  System.out.println("Here is balance - " + b);
  
    }
    catch(Exception e){

    }
    }
}
