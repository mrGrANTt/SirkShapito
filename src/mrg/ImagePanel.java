package mrg;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImagePanel extends JPanel {
    private BufferedImage image;

    public ImagePanel(String path) {
        updateImage(path);
    }

    public void updateImage(String path) {
        try {
            image = ImageIO.read(new File(path));
            repaint();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }
    }
}