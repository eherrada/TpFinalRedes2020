import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        Server serverobj=new Server(3000);
        System.out.println("Seleccione tipo de server:");
        System.out.println("1 -> Java Server\n" +
                            "2 -> Telnet Server\n");
        Integer serverType = sc.nextInt();
        serverobj.startServer(serverType);//1->Java 2->Telnet
    }
}
