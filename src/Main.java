import java.io.*;
import java.net.Socket;

public class Main {
    private final static String serverIp = "localhost";
    private final static int serverPort = 456;

    public static void main(String[] args) {
        try (
                // Подключение к серверу
                Socket socket = new Socket(serverIp, serverPort);
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ) {
            System.out.println("Connected to server. You can start sending messages:");

            String message;
            while (true) {
                message = reader.readLine();
                writer.write(message);
                writer.newLine();
                writer.flush();

                System.out.println("Sent: " + message);
            }
        } catch (IOException e) {
            System.err.println("Error during data sending: " + e.getMessage());
        }
    }
}
