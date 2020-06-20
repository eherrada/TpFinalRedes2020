import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    public static void main(String args[]) throws Exception
    {
        try{
            String host;
            Integer port;
            Scanner sc = new Scanner(System.in);
            System.out.println("Insert host:\n");
            host = sc.nextLine();
            System.out.println("Insert port:\n");
            port = sc.nextInt();
            Socket sk = new Socket(host,port);
            BufferedReader sin = new BufferedReader(new InputStreamReader(sk.getInputStream()));
            PrintStream sout=new PrintStream(sk.getOutputStream());
            BufferedReader stdin=new BufferedReader(new InputStreamReader(System.in));
            String s;
            while (  true )
            {
                System.out.print("Client : ");
                s=stdin.readLine();
                sout.println(s);
                if ( s.equalsIgnoreCase("x") )
                {
                    System.out.println("Connection ended by client");
                    break;
                }
                s=sin.readLine();
                System.out.print("Server : "+s+"\n");

            }
            sk.close();
            sin.close();
            sout.close();
            stdin.close();
        }
        catch (SocketException e){
            if(e.getMessage().equals("Software caused connection abort: recv failed")){
                System.out.println("BYE");
                System.out.println("Connection ended by server");
            }
            else{
                System.out.println(e.getMessage());
            }
        }
        catch (UnknownHostException e){
            System.out.println("Invalid Host");
        }
        catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

    }


}