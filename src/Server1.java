import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server1 {
    private final static int targetPort = 123;
    private final static int sourcePort = 456;

    private static Socket targetClient;

    public static void main(String[] args) {
        try (
                ServerSocket sourceServerSocket = new ServerSocket(sourcePort);
                ServerSocket targetServerSocket = new ServerSocket(targetPort);
        ) {
            System.out.println("Запуск сервера. Ожидание подключений...");

            new Thread(() -> {
                try {
                    targetClient = targetServerSocket.accept();
                    System.out.println("Целевой клиент подключён.");
                } catch (IOException e) {
                    System.err.println("Ошибка при подключении целевого клиента: " + e.getMessage());
                }
            }).start();

            Socket sourceClient = sourceServerSocket.accept();
            System.out.println("Исходный клиент подключён.");

            try (
                    BufferedReader sourceReader = new BufferedReader(new InputStreamReader(sourceClient.getInputStream()));
            ) {
                System.out.println("Начало ретрансляции данных...");
                String line;

                while (targetClient == null) {
                    System.out.println("Ожидание подключения целевого клиента...");
                    Thread.sleep(100);
                }

                BufferedWriter targetWriter = new BufferedWriter(new OutputStreamWriter(targetClient.getOutputStream()));

                while ((line = sourceReader.readLine()) != null) {
                    System.out.println("Получено из источника: " + line);
                    targetWriter.write(line);
                    targetWriter.newLine();
                    targetWriter.flush();
                }
                targetWriter.close();
                System.out.println("Исходный клиент закрыл соединение.");
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
