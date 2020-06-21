import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server {

    int port;
    ServerSocket server=null;
    Socket client=null;
    ExecutorService pool = null;
    int clientcount=0;

    Server(int port){
        this.port=port;
        pool = Executors.newFixedThreadPool(5);
    }

    public void startServer(int serverType) throws IOException {

        server = new ServerSocket(this.port);
        System.out.println("Server Initialized");
        System.out.println("Any client can stop the server by sending X");
        while(true)
        {
            client=server.accept();
            clientcount++;
            if(serverType == 1){
                ServerThreadJava runnable1= new ServerThreadJava(client,clientcount,this);
                pool.execute(runnable1);
            }
            else{
                ServerThread runnable2= new ServerThread(client,clientcount,this);
                pool.execute(runnable2);
            }

        }

    }

}