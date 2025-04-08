package mrg;

import javax.swing.*;
import java.awt.*;

public class HackerFrame extends JFrame {
    private static Dimension dimension = new Dimension(1920, 1080);

    private ImagePanel imagePanel;

    public HackerFrame() {
        setTitle("Test Frame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(dimension);
        try {
            imagePanel = new ImagePanel(ImageIO.read(new File("src/photo_2025-04-02_21-08-28.jpg")));
        } catch (IOException ex) {
            System.out.println("Cant read");
        }
        add(imagePanel);

        setVisible(true);
    }

    public void setImage(BufferedImage bi) {
        imagePanel.updateImage(bi);
    }
}