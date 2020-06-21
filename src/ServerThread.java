import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ServerThread implements Runnable {

    Server server=null;
    Socket client=null;
    BufferedReader cin;
    PrintStream cout;
    Scanner sc=new Scanner(System.in);
    int id;
    String s;

    ServerThread(Socket client, int count ,Server server ) throws IOException {
        this.client=client;
        this.server=server;
        this.id=count;
        System.out.println("Connection "+id+" established with client "+client);
        cin=new BufferedReader(new InputStreamReader(client.getInputStream()));
        cout=new PrintStream(client.getOutputStream());

    }

    @Override
    public void run() {
        int x=1;
        try{
            while(true){
                cout.println("Message to Server: ");
                String clientMsg=cin.readLine();
                System. out.print("Client("+id+") :"+clientMsg+"\n");
                cout.println("Client("+id+") :"+clientMsg+"\n");
                if(clientMsg.equals("x")){
                    cout.println("BYE");
                    System.out.println("BYE");
                    x=0;
                    cout.println("Connection ended by client");
                    System.out.println("Connection ended by client");
                    break;
                }
                System.out.println("Message to Client" + id + ": ");
                s = sc.nextLine();
                if (s.equals("x"))
                {
                    cout.println("BYE");
                    System.out.println("BYE");
                    x=0;cout.println("Connection ended by server");
                    System.out.println("Connection ended by server");
                    break;
                }
                cout.println("Server : \n");
                cout.println(s);
            }
            cin.close();
            client.close();
            cout.close();
            if(x==0) {
                cout.println("Server cleaning up." );
                System.out.println( "Server cleaning up." );
                System.exit(0);
            }
        }
        catch(IOException ex){
            System.out.println("Error : "+ ex);
        }


    }
}