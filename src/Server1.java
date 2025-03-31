import java.io.*;
import java.net.*;

public class Server1 {
    public static void main (String[] args) throws IOException {
        int port = 11111;
        String server2Address = "127.0.0.1";
        int server2Port = 12345;

        try (ServerSocket serverSocket = new ServerSocket(port)){
            Socket clientSocket = serverSocket.accept();
        }
    }
}
