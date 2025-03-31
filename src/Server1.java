import java.io.*;
import java.net.Socket;

public class Server1 {
    private final static String sourceIp = "localhost";
    private final static int sourcePort = 123;

    private final static String targetIp = "localhost";
    private final static int targetPort = 456;

    public static void main(String[] args) {
        try (
                Socket sourceSocket = new Socket(sourceIp, sourcePort);
                BufferedReader sourceReader = new BufferedReader(new InputStreamReader(sourceSocket.getInputStream()));

                Socket targetSocket = new Socket(targetIp, targetPort);
                BufferedWriter targetWriter = new BufferedWriter(new OutputStreamWriter(targetSocket.getOutputStream()));
        ) {
            System.out.println("Connected to source and target servers. Starting data relay...");
            String line;
            while ((line = sourceReader.readLine()) != null) {
                System.out.println("Received from source: " + line);
                targetWriter.write(line);
                targetWriter.newLine();
                targetWriter.flush();
                System.out.println("Relayed to target: " + line);
            }
            System.out.println("Source server closed the connection.");
        } catch (IOException e) {
            System.err.println("Error during data relay: " + e.getMessage());
        }
    }
}