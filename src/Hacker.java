import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Hacker {
    private final static String ip = "localhost";
    public final static int port = 123;

    public static void main(String[] args) {
        try (
                Socket sc = new Socket(ip, port);
                BufferedReader in = new BufferedReader(new InputStreamReader(sc.getInputStream()));
        ) {
            System.out.println("Server connected. Waiting new msg...");
            while (true) {
                String serverResponse = in.readLine();
                if (serverResponse != null)
                    System.out.println("Server msg: " + serverResponse);
            }
        } catch (IOException e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }
}
