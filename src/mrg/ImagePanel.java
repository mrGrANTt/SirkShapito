package mrg;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImagePanel extends JPanel {
    private BufferedImage image;

    public ImagePanel(BufferedImage bi) {
        updateImage(bi);
    }

    public void updateImage(BufferedImage bi) {
        image = bi;
        repaint();
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