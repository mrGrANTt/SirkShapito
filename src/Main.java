import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
// установите javacv и javacv-platform 1.5.11 перед сборкой
import org.bytedeco.javacv.*;
import javax.imageio.ImageIO;

public class Main {
    private final static String serverIp = "localhost";
    private final static int serverPort = 456;

    public static void main(String[] args) {
        try (
                Socket socket = new Socket(serverIp, serverPort);
                OutputStream outputStream = socket.getOutputStream();
                //BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        ) {
            FFmpegFrameGrabber grabber = new FFmpegFrameGrabber("desktop");
            grabber.setFormat("gdigrab");
            grabber.start();
            while (true) {
                Frame frame = grabber.grab();
                if (frame == null) continue;
                Java2DFrameConverter converter = new Java2DFrameConverter();
                BufferedImage Image = converter.getBufferedImage(frame);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(Image, "jpg", baos);
                byte[] imageBytes = baos.toByteArray();
                DataOutputStream dos = new DataOutputStream(outputStream);
                dos.writeInt(imageBytes.length);
                outputStream.write(imageBytes);
                outputStream.flush();
                //todo: получать это с сервера для FPS
                Thread.sleep(1000);
            }
        } catch (IOException e) {
            System.err.println("Error during data sending: " + e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
