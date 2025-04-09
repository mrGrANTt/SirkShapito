import mrg.HackerFrame;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Hacker {
    private final static String ip = "localhost";
    public final static int port = 123;

    public static void main(String[] args) {
        /*try {

            BufferedImage img = ImageIO.read(new File("C:\\Users\\mrg\\Downloads\\vecteezy_ai-generated-watercolor-painting-of-bald-eagle_41330652.png"));
            HackerFrame hf = new HackerFrame();

            Thread.sleep(3000);
            hf.setImage(img);

        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }*/

        System.out.println("Server connected. Waiting new msg...");
        HackerFrame frame = new HackerFrame();

        while (true) {
            try (
                    Socket sc = new Socket(ip, port);
            ) {
                    byte[] imgByte = read(sc);
                    BufferedImage img = ImageIO.read(new ByteArrayInputStream(imgByte));

                    frame.setImage(img);
            } catch (IOException e) {
                System.out.println("Client error: " + e.getMessage());
            }
        }
    }

    public static byte[] read(Socket sc) throws IOException {
        byte[] array = new byte[280 * 1024];
        int arrayPointer = 0;
        DataInputStream din = new DataInputStream(sc.getInputStream());
        byte[] buffer = new byte[2048];
        int readCount;

        while ((readCount = din.read(buffer)) != -1) {
            System.arraycopy(buffer, 0, array, arrayPointer, readCount);
            arrayPointer += readCount;
        }
        return array;
    }
}


// 211968b - 1920x1080 - ~207kb