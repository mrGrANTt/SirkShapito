import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;

public class Server1 {
    private final static int targetPort= 123;
    private final static int sourcePort = 456;

    public static void main(String[] args) {
        try (
                ServerSocket sourceSocket = new ServerSocket(sourcePort);
                BufferedReader sourceReader = new BufferedReader(new InputStreamReader(sourceSocket.accept().getInputStream()));

                ServerSocket targetSocket = new ServerSocket(targetPort);
                BufferedWriter targetWriter = new BufferedWriter(new OutputStreamWriter(targetSocket.accept().getOutputStream()));
        ) {
            System.out.println("Connected to source and target servers. Starting data relay...");
            String line;
            while ((line = sourceReader.readLine()) != null) {
                System.out.println("Received from source: " + line);
                targetWriter.write(line);
            }
            System.out.println("Source server closed the connection.");
        } catch (IOException e) {
            System.err.println("Error during data relay: " + e.getMessage());
        }
    }
}