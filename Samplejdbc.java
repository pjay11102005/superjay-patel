import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
public class Samplejdbc{
  public static void main(String args[]){


    String url = "jdbc:mysql://localhost:3306/JPbank";
    String user = "root";
    String password = "JSP@123jay";
    String Name = "ABC";
    String query = "INSERT INTO IT(Eid, Ename, Salary) VALUES(?,?,?)";
    String DebitQuery = "UPDATE account SET Balance = Balance - ? WHERE AccID = ?";
    String CreditQuery = "UPDATE account SET Balance =  Balance + ? WHERE AccID = ?";

    try{
      Class.forName("com.mysql.cj.jdbc.Driver");//CREATE CLASS NAME 
      Connection c = DriverManager.getConnection(url, user, password);//CREATE CONNECTION

       
                                        //check the sufficient balance in a account
      //CREATAE STATMENT FOR SQL QUERUUIES TO RUN 
  ServerSocket ssc = new ServerSocket(6000);
  Socket socket = ssc.accept();
  System.out.println("Connecting Client....--");
  BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
  PrintWriter pr = new PrintWriter(socket.getOutputStream(),true);
  PreparedStatement checkBal = c.prepareStatement("SELECT Balance FROM account WHERE AccID = ?");

String idStr = br.readLine();   // reads "310" as STRING because it gives input in STRING format only if 
                    //int a = br.read() - this will read only one character at a time ASCII  value
int id = Integer.parseInt(idStr);// converts STRING to INT

System.out.println(id); // 310
      
      checkBal.setInt(1, id);
    ResultSet rst1 =checkBal.executeQuery();
    if(rst1.next()){
      pr.println(rst1.getDouble("Balance"));
    
    }
    socket.close();
    ssc.close();


      Scanner sc = new Scanner(System.in);
  System.out.println("Enter the debited account ID - ");
  int AccID1 = sc.nextInt();
  
  System.out.println("Enter the credited account ID - ");
  int AccID2 = sc.nextInt();

  System.out.println("Enter the Amount - ");
  double amt = sc.nextDouble();

  PreparedStatement debitSt = c.prepareStatement(DebitQuery);
  PreparedStatement creditSt = c.prepareStatement(CreditQuery);

  debitSt.setInt(2, AccID1);
  debitSt.setDouble(1,amt);
  creditSt.setInt(2, AccID2);
  creditSt.setDouble(1, amt);

    PreparedStatement retriveBalance = c.prepareStatement("SELECT Balance FROM account WHERE AccID = ?");

    

    retriveBalance.setInt(1, AccID1);
    ResultSet rst = retriveBalance.executeQuery();
    if(rst.next()){
      if(amt<=rst.getDouble("Balance")){
    System.out.println("You have succesfully completed transaction 💸");

   debitSt.executeUpdate();
  creditSt.executeUpdate();
    
    }

    else{

  System.out.println("Money Couldn't be deducted");
  c.setAutoCommit(false);
    }
    
    }

    else{
      System.out.println("Account not found");
    }
  
    

//PreparedStatement st = c.prepareStatement(query);
     
//       while(true){
//       Scanner sc = new Scanner(System.in);
//       System.out.println("👽Enter the Employee name- ");
//       String Ename = sc.next();
//       System.out.println("👽Enter the Employee id - ");
//       int Eid = sc.nextInt();
//       System.out.println("💸Enter the Employee Salary- ");
//       int Salary = sc.nextInt();
      
//       st.setInt(1,Eid);
      
//       st.setString(2,Ename);//TO set value on that column 
      
//       st.setInt(3,Salary);
//       int x = st.executeUpdate();//EXECUTE QUERY
//       String choice = sc.next();
//       st.addBatch();
// System.out.println("Enter n for quit");
//       if (choice.toLowerCase().equals("n")){
//         break;
//       }

//       }

//       int[] arr = st.executeBatch();

//       for(int i=0; i<arr.length; i++){
//         System.out.println("Index  "+ (i+1)+ "-"+ arr[i]);
//       }
      // while(rs.next()){
      //   System.out.println(rs.getString("Ename"));
      // }


      
      // st.close();
      debitSt.close();
      creditSt.close();
      c.close();

    }

  catch(Exception e){
    e.printStackTrace();
  }
}
  }

  