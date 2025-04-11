import javax.xml.crypto.Data;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server1 {
    private final static int targetPort = 123; // Порт для целевого клиента
    private final static int sourcePort = 456; // Порт для исходного клиента

    private static Socket targetClient;

    public static void main(String[] args) {
        try (
                ServerSocket sourceServerSocket = new ServerSocket(sourcePort);
                ServerSocket targetServerSocket = new ServerSocket(targetPort);
        ) {
            System.out.println("Запуск сервера. Ожидание подключений...");

            // Поток для подключения целевого клиента
            new Thread(() -> {
                try {
                    targetClient = targetServerSocket.accept();
                    System.out.println("Целевой клиент подключён.");
                } catch (IOException e) {
                    System.err.println("Ошибка при подключении целевого клиента: " + e.getMessage());
                }
            }).start();

            // Подключение исходного клиента
            Socket sourceClient = sourceServerSocket.accept();
            System.out.println("Исходный клиент подключён.");

            try (
                    InputStream sourceInput = sourceClient.getInputStream(); // Получение данных от исходного клиента
            ) {
                System.out.println("Начало ретрансляции данных...");

                if (targetClient == null)
                    System.out.println("Ожидание подключения целевого клиента...");

                while (targetClient == null) {
                    Thread.sleep(100);
                }

                OutputStream targetOutput = targetClient.getOutputStream();
                while (true) {
                    DataInputStream dataInputStream = new DataInputStream(sourceInput);
                    int length = dataInputStream.readInt();
                    byte[] bytes = new byte[length];
                    dataInputStream.readFully(bytes,0,length);

                    DataOutputStream dos = new DataOutputStream(targetOutput);
                    dos.writeInt(length);
                    targetOutput.write(bytes);
                    targetOutput.flush();
                }
                //System.out.println("Исходный клиент закрыл соединение.");
            } catch (IOException | InterruptedException e) {
                System.err.println("Ошибка при передаче данных: " + e.getMessage());
            } finally {
                try {
                    sourceClient.close();
                    if (targetClient != null) {
                        targetClient.close();
                    }
                } catch (IOException e) {
                    System.err.println("Ошибка закрытия сокетов: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка запуска сервера: " + e.getMessage());
        }
    }
}
