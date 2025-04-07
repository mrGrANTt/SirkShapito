package mrg;

import javax.swing.*;
import java.awt.*;

public class HackerFrame extends JFrame {
    private static Dimension dimension = new Dimension(1920, 1080);
    boolean count = true;

    private ImagePanel imagePanel;

    public HackerFrame() {
        setTitle("Test Frame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(dimension);

        imagePanel = new ImagePanel(count ? "src/photo_2025-04-02_21-08-28.jpg" : "C:\\Users\\mrg\\Downloads\\pngtree-lot-of-food-sitting-on-a-table-picture-image_2706274.jpg");
        add(imagePanel);

        setVisible(true);

        new Timer(3000, e -> {
            setImage(count ? "src/photo_2025-04-02_21-08-28.jpg" : "C:\\Users\\mrg\\Downloads\\pngtree-lot-of-food-sitting-on-a-table-picture-image_2706274.jpg");
            count = !count;
        }).start();
    }

    public void setImage(String path) {
        imagePanel.updateImage(path);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(HackerFrame::new);
    }
}