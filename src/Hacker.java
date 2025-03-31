import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Hacker {
    private final static String ip = "localhost";
    public final static int port = 123;

    public static void main(String[] args) {
        /*
        try {

            BufferedImage img = ImageIO.read(new File("C:\\Users\\mrg\\Downloads\\vecteezy_ai-generated-watercolor-painting-of-bald-eagle_41330652.png"));

            JFrame frame = new JFrame();
            frame.getContentPane().setLayout(new FlowLayout());
            frame.getContentPane().add(new JLabel(new ImageIcon(img)));
            frame.pack();
            frame.setVisible(true);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        */

        try (
                Socket sc = new Socket(ip, port);
        ) {
            System.out.println("Server connected. Waiting new msg...");
            while (true) {
                byte[] imgByte = read(sc);
                BufferedImage img = ImageIO.read(new ByteArrayInputStream(imgByte));

                JFrame frame = new JFrame();
                frame.getContentPane().setLayout(new FlowLayout());
                frame.getContentPane().add(new JLabel(new ImageIcon(img)));
                frame.pack();
                frame.setVisible(true);

                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        } catch (IOException e) {
            System.out.println("Client error: " + e.getMessage());
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