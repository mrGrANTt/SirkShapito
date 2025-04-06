package mrg;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class HackerFrame extends JFrame {
    private static Dimension dimension = new Dimension(1920, 1080);

    public HackerFrame() {
        super("Hello, JFrame!");
        setSize(dimension);
        setPreferredSize(dimension);

        //TODO: edit
        /*this.getRootPane().addComponentListener(new ComponentAdapter() {
            int lastWight = 1920;
            int lastHeight = 1080;

            public void componentResized(ComponentEvent e) {
                int height = e.getComponent().getHeight();
                int wight = e.getComponent().getWidth();
                if(lastHeight != height) {
                    double w = 1080d / 1920;
                    System.out.println(w);
                    System.out.println((int) (w * height));
                    dimension.width = (int) (w * height);
                } else if (lastWight != wight) {
                    double h = 1920d / 1080;
                    System.out.println(h);
                    System.out.println((int) (h * wight));
                    dimension.height = (int) (h * wight);
                }
                lastHeight = e.getComponent().getHeight();
                lastWight = e.getComponent().getWidth();

                e.getComponent().setSize(dimension);
                System.out.println("componentResized");
            }
        });*/
    }

    public static void main(String[] args) throws IOException {
        HackerFrame jFrame = new HackerFrame();
        BufferedImage img = ImageIO.read(new File("C:\\Users\\mrg\\Downloads\\vecteezy_ai-generated-watercolor-painting-of-bald-eagle_41330652.png"));


        ImageIcon imageIcon = new ImageIcon(img);
        JLabel label = new JLabel(imageIcon);
        label.setSize(dimension);
        jFrame.getContentPane().add(label, BorderLayout.CENTER);

        jFrame.setVisible(true);
    }
}
